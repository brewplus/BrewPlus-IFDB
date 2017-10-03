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

import javax.swing.table.TableColumn;

import jmash.*;
import jmash.Main.BitterBUGU;
import jmash.config.ConfigurationManager;
import jmash.config.bean.GeneralConfig;

public class SummaryTableModel extends GenericTableModel<Hop> {

    private static final long serialVersionUID = -5667189467722852137L;
    private static GeneralConfig generalConfig = ConfigurationManager.getIstance().getGeneralConfig();
    
    public static final int TINSETH_COLUMN=4;
	public static final int RAGER_COLUMN=5;
	public static final int DANIELS_COLUMN=6;
	public static final int BU_GU_COLUMN=7;
	
    private Ricetta ricetta;
    private static final String[] cN = new String[] { "OG", "Plato", "OG pre-Boil", "°P pre-Boil", "Tinseth", "Rager",
            "Daniels", "BU/GU", "Tot. Grani", "Tot. Luppoli", "Mash pH"};

    public SummaryTableModel(Ricetta ricetta) {
        this.setRicetta(ricetta);
        this.setBUGUratio();
        this.columnNames = cN;
    }

    @Override
    public int getColumnCount() {
        return this.columnNames == null ? 0 : this.columnNames.length;
    }

    private double SG = 0;
    private double PLATO;
    private double SGPB = 0;
    private double PLATOPB;
    private String sSG, sP;

    public void setSG(double SG) {
        this.SG = SG;
        this.setPLATO(Utils.SG2Plato(SG));
        sSG = NumberFormatter.format03(SG);
        sP = NumberFormatter.format02(this.getPLATO()) + " °P";
    }

    private double IBU = 0;
    private double IBU2 = 0;
    private double IBUGaretz = 0;
    private double IBUDaniels = 0;
    private double IBURager = 0;
    private String sIBU, sIBU2, sIBUD;
    private String mashPH = "NaN";

    public void setIBU(double IBU) {
        this.IBU = IBU;
        sIBU = NumberFormatter.format01(IBU) + " IBU";
    }

    public void setIBU2(double IBU2) {
        this.IBU2 = IBU2;
        sIBU2 = NumberFormatter.format01(IBU2) + " IBU";
    }

    public void setIBUGaretz(double IBUGaretz) {
        this.IBUGaretz = IBUGaretz;
    }

    public void setIBUDaniels(double IBUDaniels) {
        this.IBUDaniels = IBUDaniels;
        sIBUD = NumberFormatter.format01(IBUDaniels) + " IBU";
    }

    public void setIBURager(double IBURager) {
        this.IBURager = IBURager;
    }
    
    public void setMashPH(double mashPH) {
        this.mashPH = Double.isNaN(mashPH) ?  "NaN" : NumberFormatter.format03(mashPH);
    }
    
    public double getMashPH() {
        return "NaN".equals(mashPH) ? Double.NaN : Double.parseDouble(mashPH);
    }

    private int totG = 0;

    public int getTotG() {
        return this.totG;
    }

    private double totL = 0;

    public double getTotL() {
        return this.totL;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
        case 0:
            return sSG;
        case 1:
            return sP;
        case 2:
            return sSGPB;
        case 3:
            return sPPB;
        case 4:
        	String tiporatioBU = generalConfig.getBUGUratiostring();
        	if (BitterBUGU.TIN.equals(tiporatioBU))
        		return sIBU;
            if (BitterBUGU.DAN.equals(tiporatioBU))
            	return sIBUD; 
            if (BitterBUGU.RAG.equals(tiporatioBU))
            	return sIBU2;
        case 5:
            return sIBU2;
        case 6:
            return sIBUD;
        case 7:
            double iburatio = 0;
            String tiporatio = generalConfig.getBUGUratiostring();
            if (BitterBUGU.TIN.equals(tiporatio))
                iburatio = this.getIBU();
            if (BitterBUGU.DAN.equals(tiporatio))
                iburatio = this.getIBUDaniels();
            if (BitterBUGU.RAG.equals(tiporatio))
                iburatio = this.getIBU2();
            if (Double.isNaN(iburatio / ((getSG() - 1) * 1000)))
            {
              return NumberFormatter.format02(0.0);
            }
            return NumberFormatter.format02(iburatio / ((getSG() - 1) * 1000));
        case 8:
            return sTotG;
        case 9:
            return sTotL;
        case 10:
            return mashPH;

        default:
            return "";
        }
    }

    private Boolean partyGyle;

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    }

    public Boolean getPartyGyle() {
        return partyGyle;
    }

    public void setPartyGyle(Boolean partyGyle) {
        this.partyGyle = partyGyle;
    }

    public Ricetta getRicetta() {
        return ricetta;
    }

    public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }

    public void setBUGUratio() {
        if (generalConfig.getBUGUratiostring().equals(BitterBUGU.TIN))
            cN[7] = "BU/GU Tinseth";
        else if (generalConfig.getBUGUratiostring().equals(BitterBUGU.RAG))
            cN[7] = "BU/GU Rager";
        else if (generalConfig.getBUGUratiostring().equals(BitterBUGU.DAN))
            cN[7] = "BU/GU Daniels";
        if (columnNames != null)
        {
        	columnNames[7] = cN[7];
        }	
    }
    
   

    public double getSG() {
        return SG;
    }

    public double getPLATO() {
        return PLATO;
    }

    public void setPLATO(double PLATO) {
        this.PLATO = PLATO;
    }

    public double getSGPB() {
        return SGPB;
    }

    private String sSGPB, sPPB;

    public void setSGPB(double SGPB) {
        this.SGPB = SGPB;
        this.setPLATOPB(Utils.SG2Plato(SGPB));
        sSGPB = NumberFormatter.format03(this.getSGPB());
        sPPB = NumberFormatter.format02(this.getPLATOPB()) + " °P";
    }

    public double getPLATOPB() {
        return PLATOPB;
    }

    public void setPLATOPB(double PLATOPB) {
        this.PLATOPB = PLATOPB;
    }

    public double getIBU() {
        return IBU;
    }

    public double getIBU2() {
        return IBU2;
    }

    public double getIBUGaretz() {
        return IBUGaretz;
    }

    public double getIBUDaniels() {
        return IBUDaniels;
    }

    public double getIBURager() {
        return IBURager;
    }

    private String sTotG, sTotL;

    public void setTotG(int totG) {
        this.totG = totG;
        sTotG = NumberFormatter.format00(getTotG()) + " gr";
    }

    public void setTotL(double totL) {
        this.totL = totL;
        sTotL = NumberFormatter.format00(getTotL()) + " gr";
    }
    
    

    
}
