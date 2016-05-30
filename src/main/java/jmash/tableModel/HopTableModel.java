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

import javax.swing.JButton;
import javax.swing.JLabel;
import jmash.*;

/**
 *
 * @author Alessandro
 */
public class HopTableModel extends GenericTableModel<Hop> {
    
    /**
     *
     */
    private static final long serialVersionUID = -54392409816219092L;
    Ricetta ricetta;
    private static String[] hopColumnNames= new String[]{"", "Nome", "Q.tà ", "U.mis.", "Forma", "Alfa A.", "Bollitura", "Uso", "Tinseth", "Rager", "Daniels", ""};
    public HopTableModel(Ricetta ric) {
	this.ricetta = ric;
	ret.setIcon(Main.clockIcon);
	this.columnNames = hopColumnNames;
    }
    private static JButton ret = new JButton("");
    private static JLabel icon = new JLabel("");

    @Override
    public Object getValueAt(int row, int col) {
	Hop h = this.dataValues.get(row);
	
	if (h != null) {
	    if (h.getForma() != null && h.getForma().compareToIgnoreCase("spice") == 0) {
		icon.setIcon(Main.spiceIcon);
	    } else {
                icon.setIcon(Main.hopIcon);
            }
	    switch (col) {
		case 0:
		    return icon;
		case 1:
		    return h.getNome();
		case 2:
                    if (h.getForma() != null && h.getForma().compareToIgnoreCase("spice") == 0) {
                        //Dario: alcune spezie hanno la necessità di essere dosate con precisione al decimo di grammo
                        hmFormatterUM.remove("grammi");
                        hmFormatterUM.put("grammi",new NumberFormatter("0.0"));
                    } else {
                        hmFormatterUM.remove("grammi");
                        hmFormatterUM.put("grammi",new NumberFormatter("0"));
                    }
                    return (hmFormatterUM.get(h.getUnitaMisura())).format(h.getConvertedGrammi());
		case 3:
		    return h.getUnitaMisura();
		case 4:
		    return h.getForma();
		case 5:
		    return NumberFormatter.format01(h.getAlfaAcidi());
		case 6:
		    return h.getBB();
		case 7:
		    return h.getUso();
		case 8:
		    return NumberFormatter.format01(h.getIBUTinseth());
		case 9:
		    return NumberFormatter.format01(h.getIBURager());
		case 10:
		    return NumberFormatter.format01(h.getIBUDaniels());
		case 11:
		    return ret;
	    }
	}
	return null;
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
	return //false;
		((col < 8) && (col > 0));
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
	try{
	    if (this.dataValues.get(row) != null) {
		
		Hop h = (this.dataValues.get(row));
		boolean flag = false;
		switch (col) {
		    case 1:
			h.setNome(((String) value));
			break;
		    case 2:
                        h.setGrammi(
                            Utils.convertWeight(NF.parse((String)value).doubleValue(), h.getUnitaMisura(), "grammi"));
			break;
		    case 3:
			h.setUnitaMisura(((String) value));
			break;
		    case 4:
			h.setForma((String) value);
			break;
		    case 5: 	
			h.setAlfaAcidi(NF.parse((String) value).doubleValue());
			break;
		    case 6:
			h.setBoilTime(((Integer) value));
			break;
		    case 7:
			h.setUso((String) value);
			break;
			
		    default:
			break;
		}
		fireTableRowsUpdated(row,row);
	    }
	} catch (ParseException ex) {
	    Utils.showException(ex);
	}
    }
    
    public static double getIBUTinseth(List<Hop> hops, double volume, double volumeDil, double OG) {
	double ibu = 0;
	if(hops!=null)
	    for (Hop h : hops) {
		ibu += h.getIBUTinseth();
	    }
	    return ibu;
    }
    
    public double getIBUTinseth() {
	double ibu = 0;
	if(this.dataValues!=null)
	    for (Hop h : this.dataValues) {
		ibu += h.getIBUTinseth();
	    }
	    return ibu;
    }
    
    public double getIBURager() {
	double ibu = 0;
	if(this.dataValues!=null)
	    for (Hop h : this.dataValues) {
		ibu += h.getIBURager();
	    }
	    return ibu;
    }
    
    public double getIBUDaniels() {
	double ibu = 0;
	if(this.dataValues!=null)
	    for (Hop h : this.dataValues) {
		ibu += h.getIBUDaniels();
	    }
	    return ibu;
    }
    
    /**
     * grazie a Faromagio per la sua guida
     */
    public double getIBUGaretz() {
	double ibu = 0;
	double temp = 0;
	if(this.dataValues!=null)
	    for (Hop h : this.dataValues) {
		double ut = 18.11 + 13.86 * Hop.hypTan((h.getBoilTime() - 31.32) / 18.27);
		
		temp += ut * h.getGrammi() * h.getAlfaAcidi();
	    }
	    
	    double vb = this.ricetta.getVolumeBoll();
	    double vf = this.ricetta.getVolume();
	    double bg = (vf / vb) * (this.ricetta.getGravity() - 1) + 1;
	    double gf = 5 * (bg - 0.85);
	    double tf = (Main.config.getMetriSLM() / 168) * 0.02 + 1;
	    
	    ibu += 130 * (-1 + Math.sqrt(1 + temp / (650 * vb * gf * tf))) / (vf / vb);
	    
	    return ibu;
    }
    
    public double getGrammi() {
	double gr = 0;
	if(dataValues!=null)
	    for (Hop h : this.dataValues) {
		if (h.getAlfaAcidi() > 0) {
		    gr += h.getGrammi();
		}
	    }
	    return gr;
    }
    
    public void adjustTo(int effTo, double volTo, int effFrom, double volFrom) {
	if(dataValues!=null)
	    for (Hop h : this.dataValues) {
		BigDecimal bd = new BigDecimal(h.getGrammi());
		BigDecimal eff = new BigDecimal(effTo);
		eff = eff.divide(new BigDecimal(effFrom), 32, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal vol = new BigDecimal(volTo);
		eff = eff.divide(new BigDecimal(volFrom), 32, BigDecimal.ROUND_HALF_DOWN);
		
		bd = bd.multiply(eff);
		bd = bd.multiply(vol);
		
		h.setGrammi(bd.doubleValue());
	    }
	    fireTableDataChanged();
    }
}
