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

import java.text.Format;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class GenericTableModel<T> extends javax.swing.table.DefaultTableModel{
    
    /**
     *
     */
    private static final long serialVersionUID = 6071210525849454744L;
    protected String columnNames[];
    protected List<T> dataValues=new LinkedList<T>();
    protected static HashMap<String,Format> hmFormatterUM=new HashMap<String,Format>();
    {
	hmFormatterUM.put("grammi",new NumberFormatter("0"));
	hmFormatterUM.put("kg",new NumberFormatter("0.000"));
	hmFormatterUM.put("ounces",new NumberFormatter("0.00"));
	hmFormatterUM.put("pounds",new NumberFormatter("0.00"));
    }

    
    protected static NumberFormat NF= NumberFormat.getInstance();
    public T getRow(int i){
        if(i>=0) {
            return this.dataValues.get(i);
        }
        return null;
    }
    public T getLastRow(){
        if(dataValues.size()==0)return null;
        return this.dataValues.get(dataValues.size()-1);
    }    
    public int addRow(T m){
        this.dataValues.add(m);
        fireTableDataChanged();
	return this.dataValues.indexOf(m);
    }
    public void setRows(List<T> L){
        this.dataValues=L;
        fireTableDataChanged();
    }
    public void remRow(int i){
        if(i>=0){
            this.dataValues.remove(i);
            fireTableDataChanged();
        }
    }
    public void remAllRows(){
        if(dataValues.size()>0){
            this.dataValues.clear();
            fireTableDataChanged();
        }
    }
    public List<T> getRows(){
        return this.dataValues;
    }
    public void clear(){
        this.dataValues.clear();
        fireTableDataChanged();
    }
    @Override
    public String getColumnName(int col) {
        return this.columnNames[col].toString();
    }
    @Override
    public int getRowCount() {
        return this.dataValues==null?0:this.dataValues.size();
    }
    @Override
    public int getColumnCount() {
        return this.columnNames==null?0:this.columnNames.length;
    }
    
    @Override
    public Class<? extends Object> getColumnClass(int col) {
        if (getRowCount() == 0) {
            return Object.class;
        } else {
            Object cellValue = getValueAt(0, col);
            if(cellValue!=null) {
                return cellValue.getClass();
            } else {
                return Object.class;
            }
        }
    }
    public int getColumnWidth(int col){
        return 320;
    }
}
