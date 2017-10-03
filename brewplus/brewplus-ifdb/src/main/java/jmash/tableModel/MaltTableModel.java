/*
 
 *
 *  This file is part of BrewPlus.
 *
 *  BrewPlus is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  BrewPlus is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BrewPlus; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package jmash.tableModel;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

import jmash.*;

/**
 *
 * @author Alessandro
 * 
 */
public class MaltTableModel extends GenericTableModel<Malt> {

    /**
     *
     */
    private static final long serialVersionUID = -1437528549102631806L;
    private static final Logger LOGGER = Logger.getLogger(MaltTableModel.class);
    Ricetta ricetta;
    private static String[] maltColumnNames = new String[] { "", "Malti e zuccheri", "Quantita'", "Un.mis.", "%", "Pot. SG",
            "Forma", "Colore EBC", "Colore SRM", "Late Addiction" };

    public MaltTableModel(Ricetta ricetta) {
        this.ricetta = ricetta;
        ret.setIcon(Main.maltIcon);
        this.columnNames = maltColumnNames;
    }

    private static JLabel ret = new JLabel("");

    @Override
    public Object getValueAt(int row, int col) {
        Malt m = this.dataValues.get(row);

        ret.setIcon(Main.maltIcon);
        if (m.getForma() != null && m.getForma().compareToIgnoreCase("estratto liquido") == 0) {
            ret.setIcon(Main.extractIcon);
        }
        if (m != null) {
            switch (col) {
            case 2:
            	/** ISSUE #47 */
            	Quantita qnt = new Quantita(hmFormatterUM.get(m.getUnitaMisura()).format(m.getConvertedGrammi()));
            	qnt.setUnitaMisura(m.getUnitaMisura());
                return qnt;
            case 3:
                return m.getUnitaMisura();
            case 4:
                m.setPercentuale((int) Math.round(100 * m.getGrammi() / (double) getTotGrammi()));
                return (double) Math.round(100 * m.getGrammi() / (double) getTotGrammi());
            case 5:
                return NumberFormatter.format03(m.getPotentialSG());
            case 6:
                return m.getForma();
            case 7:
                return NumberFormatter.format01(m.getEbc());
            case 8:
                return NumberFormatter.format01(m.getSrm());
            case 9:
                return m.getLateAddiction();
            case 1:
                return m.getNome();
            default:
                return ret;
            }
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Malt m = this.dataValues.get(row);
        try {
            if ((m != null) && (value != null)) {
                switch (col) {
                case 2:
                	/** ISSUE #47 */
                    m.setGrammi(
                            Utils.convertWeight(NF.parse(((Quantita) value).getValue()).doubleValue(), m.getUnitaMisura(), "grammi"));
                    break;
                case 3:
                    m.setUnitaMisura(((String) value));
                    break;
                case 5:
                    m.setPotentialSG(NF.parse((String) value).doubleValue());
                    break;
                case 6:
                    m.setForma((String) value);
                    break;
                case 7:
                    m.setEbc(NF.parse((String) value).doubleValue());
                    break;
                case 8:
                    m.setSrm(NF.parse((String) value).doubleValue());
                    break;
                case 9:
                    m.setLateAddiction((boolean) value);
                    break;
                case 1:
                    m.setNome(((String) value));
                case 4:
                    m.setPercentuale((int) (Math.round((100 * m.getGrammi()) / (double) getTotGrammi())));
                default:
                    break;
                }
            }
        } catch (ParseException ex) {
            LOGGER.error(ex.getCause());
        }
        fireTableCellUpdated(row, col);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return !(col == 4 || col == 0);
    }

    public int getGrammi() {
        int gr = 0;
        if (dataValues != null)
            for (Malt m : this.dataValues) {
                gr += m.getGrammi();
            }
        return gr;
    }

    public int getGrammiMash() {
        int gr = 0;
        if (dataValues != null)
            for (Malt m : this.dataValues) {
                if (m.isMashed()) {
                    gr += m.getGrammi();
                }
            }
        return gr;
    }

    public int getTotGrammi() {
        int gr = 0;
        if (dataValues != null)
            for (Malt m : this.dataValues) {
                gr += m.getGrammi();
            }
        return gr;
    }

    public double getSG(boolean concentrated) {
        double volume = concentrated ? this.ricetta.getVolumeDiluito() : this.ricetta.getVolume();
        return MaltTableModel.calcolaSG(dataValues, volume, this.ricetta.getEfficienza());
    }
    
    // return SG for IBU calculation (Late addiction not included)
    public double getSGIBU(boolean concentrated) {
        double volume = concentrated ? this.ricetta.getVolumeDiluito() : this.ricetta.getVolume();
        return MaltTableModel.calcolaSGIBU(dataValues, volume, this.ricetta.getEfficienza());
    }

    public static double calcolaSG(List<Malt> malts, double volume, double efficienza) {
        double sg = 1;
        double partialSg = 0;
        int flagPot = Main.config.getPotLibGal();
        //LOGGER.debug("flagPot = " + Main.config.getPotLibGal());
        if (malts != null)
            for (Malt m : malts) {
                double r = 1;
                if (m.isMashed()) {
                    r = ((double) efficienza / 100);
                }
                if (m.getPotentialSG() != null) {
                    // grammi Lt - libbre Gal  
                    partialSg = r * ((double) m.getGrammi() / 1000) * ((double) m.getPotentialSG() - 1) * (10 / volume);
                    if ( flagPot == 1) {
                        partialSg *= (0.835);
                        //LOGGER.debug("Rescaling misurement by 0.835");
                    } 
                    sg += partialSg;
                    //LOGGER.debug("Partial Sg for malt [" + m.getNome() + " - " + m.getPotentialSG() + "] = " + partialSg + " / " + sg);
                }
            }
        //LOGGER.debug("Gravity (total) = " + sg);
        return sg;
    }
    
    // return SG for IBU calculation (Late addiction not included)
    public static double calcolaSGIBU(List<Malt> malts, double volume, double efficienza) {
        double sg = 1;
        double partialSg = 0;
        int flagPot = Main.config.getPotLibGal();
        //LOGGER.debug("flagPot = " + Main.config.getPotLibGal());
        if (malts != null)
            for (Malt m : malts) {
                if(!m.getLateAddiction()){
                    double r = 1;
                    if (m.isMashed()) {
                        r = ((double) efficienza / 100);
                    }
                    if (m.getPotentialSG() != null) {
                        // grammi Lt - libbre Gal  
                        partialSg = r * ((double) m.getGrammi() / 1000) * ((double) m.getPotentialSG() - 1) * (10 / volume);
                        if ( flagPot == 1) {
                            partialSg *= (0.835);
                            //LOGGER.debug("Rescaling misurement by 0.835");
                        } 
                        sg += partialSg;
                        //LOGGER.debug("Partial Sg for malt [" + m.getNome() + " - " + m.getPotentialSG() + "] = " + partialSg + " / " + sg);
                    }
                }
            }
        //LOGGER.debug("Gravity (for IBU) = " + sg);
        return sg;
    }

    public static double calcolaSRMMosher(List<Malt> malts, double volume) {
        double srm = 0;
        double mcu = 0;
        if (malts != null)
            for (Malt m : malts) {
                mcu += m.getMcu(volume);
            }
        srm = (mcu * 0.3) + 4.7;
        if (mcu == 0) {
            return 0;
        }
        return srm;
    }

    public double getSRMMosher() {
        double srm = 0;
        double mcu = 0;
        double volume = this.ricetta.getVolumeDopoEventualeDiluizione();
        if (dataValues != null)
            for (Malt m : this.dataValues) {
                mcu += m.getMcu(volume);
            }
        srm = (mcu * 0.3) + 4.7;
        if (mcu == 0) {
            return 0;
        }
        return srm;
    }

    public double getSRMDaniels() {
        double srm = 0;
        double mcu = 0;
        double volume = this.ricetta.getVolumeDopoEventualeDiluizione();
        if (dataValues != null)
            for (Malt m : this.dataValues) {
                mcu += m.getMcu(volume);
            }

        srm = (mcu * 0.2) + 8.4;
        if (mcu == 0) {
            return 0;
        }
        return srm;
    }

    public double getSRMMorey() {
        double srm = 0;
        double mcu = 0;
        double volume = this.ricetta.getVolumeDopoEventualeDiluizione();
        if (dataValues != null)
            for (Malt m : this.dataValues) {
                mcu += m.getMcu(volume);
            }

        srm = 1.4922 * Math.pow(mcu, 0.6859);
        if (mcu == 0) {
            return 0;
        }
        if (srm > 50) {
            return getSRMMosher();
        }
        return srm;
    }

    public void adjustTo(double effTo, double volTo, double effFrom, double volFrom) {
        if (dataValues != null)
            for (Malt m : this.dataValues) {
                BigDecimal bd = new BigDecimal(m.getGrammi());
                BigDecimal eff = new BigDecimal(effTo);
                eff = eff.divide(new BigDecimal(effFrom), 32, BigDecimal.ROUND_HALF_DOWN);
                BigDecimal vol = new BigDecimal(volTo);
                eff = eff.divide(new BigDecimal(volFrom), 32, BigDecimal.ROUND_HALF_DOWN);

                bd = bd.multiply(eff);
                bd = bd.multiply(vol);
                m.setGrammi(bd.doubleValue());
                fireTableDataChanged();
            }
    }
}
