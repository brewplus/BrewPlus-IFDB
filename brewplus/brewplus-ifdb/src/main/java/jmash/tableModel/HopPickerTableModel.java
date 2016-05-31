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
public class HopPickerTableModel extends PickerTableModel{
    
    /**
     *
     */
    private static final long serialVersionUID = 7787910033826801307L;
    /** Creates a new instance of HopTableModel */
    LinkedList<HopType> dataValues=new LinkedList<HopType>();
    public HopPickerTableModel() {
    }
    String columnNames[] = { "Nome", "Origine", "Alfa Acidi", "Caratteristiche" };
    
    public void addRow(HopType h){
        this.dataValues.add(h);
        fireTableDataChanged();
    }
    @Override
    public void emptyRows(){
        this.dataValues.clear();
        fireTableDataChanged();
    }
    @Override
    public List<HopType> getRows(){
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
        HopType h=this.dataValues.get(row);
        if(h!=null){
            switch(col){
                case 1:
                    return  h.getProvenienza();
                case 2:
                    return  h.getAlfaAcidi();
                case 3:
                    return  h.getCaratteristiche();
                case 0:
                default:
                    return h.getNome();
            }
        }
        return null;
    }
    
}
