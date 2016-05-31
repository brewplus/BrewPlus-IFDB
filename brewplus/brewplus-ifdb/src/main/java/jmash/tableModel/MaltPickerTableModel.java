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

import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import jmash.*;

/**
 *
 * @author Alessandro
 */
public class MaltPickerTableModel extends PickerTableModel{

    /**
	 * 
	 */
    private static final long serialVersionUID = 2273174133360604792L;
    private JLabel ret=new JLabel("");
	/** Creates a new instance of HopTableModel */
    LinkedList<MaltType> dataValues=new LinkedList<MaltType>();
    public MaltPickerTableModel() {
    }
    String columnNames[] = { "","Nome", "Potenziale","Colore", "Forma", "Origine",   "Caratteristiche" };

    public void addRow(MaltType m){
            this.dataValues.add(m);
            fireTableDataChanged();
    }
    @Override
	public void emptyRows(){
        this.dataValues.clear();
        fireTableDataChanged();
    }
    @Override
	public List<MaltType> getRows(){
        return this.dataValues;
    }
    @Override
	public String getColumnName(int col) {
        return this.columnNames[col].toString();
    }
    @Override
	public int getRowCount() { return this.dataValues==null?0:this.dataValues.size(); }
    @Override
	public int getColumnCount() { return columnNames.length; }
    @Override
	public Object getValueAt(int row, int col) {
        MaltType m=this.dataValues.get(row);
	this.ret.setIcon(Main.maltIcon);

        if(m!=null){
	if(m.getForma()!=null && m.getForma().compareToIgnoreCase("estratto liquido")==0) {
	    this.ret.setIcon(Main.extractIcon);
	}		    
                switch(col){
			case 6: return  m.getCaratteristiche()==null?m.getNotes():m.getCaratteristiche();
                        case 5: return  m.getOrigine();
                        case 4: return  m.getForma();
                        case 3: return  m.getEbc();
			case 2: return  m.getSg();
                        case 1: return m.getNome();
                        default:
				return this.ret;    
                }
        }
        return null;
    }

    @Override
	public boolean isCellEditable(int row, int col)
        { return false; }
    @Override
	public void setValueAt(Object value, int row, int col) {
    }
    
    LinkedList<MaltType> dataValuesCopy=null;
    public void setFilterOn(int column, String VAL){
	if(dataValuesCopy==null)dataValuesCopy=dataValues;
	dataValues=dataValuesCopy;
	LinkedList<MaltType> dataValuesNew=new LinkedList<MaltType>();
	for (int i = 0; i < dataValuesCopy.size(); i++) {
	    if(getValueAt(i,column)!=null && getValueAt(i,column).toString().compareToIgnoreCase(VAL)==0)
		dataValuesNew.add(dataValuesCopy.get(i));
	}
	dataValues=dataValuesNew;
	fireTableDataChanged();
    }
    public void setFilterOff(){
	if(dataValuesCopy!=null)dataValues=dataValuesCopy;
	fireTableDataChanged();
    }
}    
