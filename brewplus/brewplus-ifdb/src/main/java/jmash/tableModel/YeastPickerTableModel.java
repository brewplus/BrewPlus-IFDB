/*
 *  Copyright 2006, 2007 Alessandro Chiari.
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
import jmash.*;

/**
 *
 * @author Alessandro
 */
public class YeastPickerTableModel extends PickerTableModel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4140162277202108851L;

	/** Creates a new instance of YeastPickerTableModel */
    public YeastPickerTableModel() {
    }
    LinkedList<YeastType> dataValues=new LinkedList<YeastType>();
    String columnNames[] = { "Produttore", "Codice", "Nome", "Forma" };

    public void addRow(YeastType h){
        this.dataValues.add(h);
        fireTableDataChanged();
    }
    @Override
	public void emptyRows(){
        this.dataValues.clear();
        fireTableDataChanged();
    }
    @Override
	public List<YeastType> getRows(){
        return this.dataValues;
    }
    @Override
	public String getColumnName(int col) {
        return this.columnNames[col].toString();
    }
    @Override
	public int getRowCount() { return this.dataValues==null?0:this.dataValues.size(); }
    @Override
	public int getColumnCount() { return 3; }
    @Override
	public Object getValueAt(int row, int col) {
        YeastType h=this.dataValues.get(row);
        if(h!=null){
            switch(col){
                case 1:
                    return  h.getCodice();
                case 2:
                    return  h.getNome();
                case 3:
                    return  h.getForma();
                case 0:
                default:
                    return h.getProduttore();
            }
        }
        return null;
    }
}
