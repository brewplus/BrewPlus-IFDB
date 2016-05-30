/*
 *  
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

import java.text.ParseException;
import java.util.Date;
import javax.swing.JLabel;
import jmash.*;

/**
 *
 * @author Alessandro
 */
public class MaltBuyTableModel  extends GenericTableModel<Malt>{
    
    public MaltBuyTableModel() {
	this.ret.setIcon(Main.maltIcon);
	this.columnNames = new String[]{"", "Malti e zuccheri", "Q.tà ", "U.mis.", "Pot. SG","Forma", "Colore", "Origine", "Data rif." };
    }
    private JLabel ret=new JLabel("");
    
    @Override
    public Object getValueAt(int row, int col) {
	Malt m=this.dataValues.get(row);
	
	this.ret.setIcon(Main.maltIcon);
	if(m.getForma().compareToIgnoreCase("estratto liquido")==0) {
	    this.ret.setIcon(Main.extractIcon);
	}
	if(m!=null){
	    switch(col){
		case 1:
		    return m.getNome();
		case 2:
		    return (hmFormatterUM.get(m.getUnitaMisura())).format(m.getConvertedGrammi());
		case 3:
		    return  m.getUnitaMisura();
		case 4:
		    return NumberFormatter.format03(m.getPotentialSG());
		case 5:
		    return  m.getForma();
		case 6:
		    return NumberFormatter.format01(m.getEbc());
		case 7:
		    return  m.getOrigine();
		case 8:
		    return  m.getDataAcquisto();
		    
		default:
		    return this.ret;
	    }
	}
	return null;
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
	Malt m=this.dataValues.get(row);
	try {
	    if((m!=null) && (value!=null)){
		switch(col){
		    case 2:
			m.setGrammi(
				Utils.convertWeight(NF.parse((String)value).doubleValue(), m.getUnitaMisura(), "grammi"));
			break;
		    case 3:
			m.setUnitaMisura(((String)value));
			fireTableRowsUpdated(row,row);
			break;
		    case 4:
			m.setPotentialSG(NF.parse((String)value).doubleValue());
			break;
		    case 5:
			m.setForma((String)value);
			break;
		    case 6:
			m.setEbc(NF.parse((String)value).doubleValue());
			break;
		    case 7:
			m.setOrigine((String)value);
			break;
		    case 8:
			m.setDataAcquisto((Date)value);
			break;
		    case 1:
			m.setNome(((String)value));
		    default:
			break;
		}
	    }
	} catch (ParseException ex) {
	    Utils.showException(ex);
	}
	fireTableCellUpdated(row, col);
    }
    
    
    @Override
    public boolean isCellEditable(int row, int col){
	return true;
    }
    
    
}