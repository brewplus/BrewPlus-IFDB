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

import jmash.interfaces.InventoryObject;

/**
 *
 * @author Alessandro
 */
public class InventoryObjectTableModel extends GenericTableModel<InventoryObject>{
    
    public InventoryObjectTableModel() {
	this.columnNames = new String[]{"", "Nome", "Q.tà "};
    }
    
    @Override
    public Object getValueAt(int row, int col) {
	InventoryObject h=this.dataValues.get(row);
	if(h!=null){
	    switch(col){
		case 0:
		    return  h.isSelected();
		case 1:
		    return h.getNome();
		case 2:
		    return  h.getGrammi();
	    }
	}
	return null;
    }
    @Override
    public Class<? extends Object> getColumnClass(int col) {
	switch(col){
	    case 0:
		return  Boolean.class;
	    case 1:
		return String.class;
	    case 2:
		return  Double.class;
	}
	return Object.class;
    }
    @Override
    public boolean isCellEditable(int row, int col){
	return col==0;
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
	if(this.dataValues.get(row)!=null){
	    InventoryObject h=(this.dataValues.get(row));
	    boolean flag=false;
	    switch(col){
		case 0:
		    h.setSelected((Boolean)value); break;
		default:
		    break;
	    }
	    fireTableCellUpdated(row, col);
	    if(flag) {
		fireTableDataChanged();
	    }
	}
    }
    
    
}