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
import jmash.*;

/**
 *
 * @author Alessandro
 */
public class BrewStylePickerTableModel extends PickerTableModel {
    
    /**
     *
     */
    private static final long serialVersionUID = 6085765690344228808L;
    /** Creates a new instance of HopTableModel */
    List<BrewStyle> dataValues=new LinkedList<BrewStyle>();
    public BrewStylePickerTableModel() {
    }
    String columnNames[] = { "Codice", "Categoria", "Nome" };
    
    public void addRow(BrewStyle m){
        this.dataValues.add(m);
        fireTableDataChanged();
    }
    @Override
    public void emptyRows(){
        this.dataValues.clear();
        fireTableDataChanged();
    }
    @Override
    public List<BrewStyle> getRows(){
        return this.dataValues;
    }
    public void setRows(List<BrewStyle> rows){
        this.dataValues=rows;
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
        BrewStyle m=this.dataValues.get(row);
        if(m!=null){
            switch(col){
                case 1:
                    return  m.getCategoria();
                case 2:
                    return  m.getNome();
                case 0:
                	return m.getNumero();
                default:
                    return m.getNumero();
            }
        }
        return null;
    }
    
}
