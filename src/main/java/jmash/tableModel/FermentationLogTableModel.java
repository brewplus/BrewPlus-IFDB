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

import java.lang.reflect.Method;
import jmash.*;

/**
 *
 * @author AChiari
 */
public class FermentationLogTableModel extends GenericTableModel<SGLog>{
    
    public FermentationLogTableModel() {
        this.columnNames=new String[]  {    "Data", "SG","°P","°C", "pH" };
    }
    
    
    Ricetta ricetta;
    PanelFermentationLog panel;
    public FermentationLogTableModel(Ricetta ricetta) {
        this();
        this.ricetta=ricetta;
    }
    public FermentationLogTableModel(PanelFermentationLog panel) {
        this();
        this.panel=panel;
    }
    
    String fieldNames[] = {   "Data","SG", "Plato","T", "pH"};
    
    public void removeAll(){
        this.dataValues.clear();
        fireTableDataChanged();
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        SGLog h=this.dataValues.get(row);
        try{
            Method m=h.getClass().getMethod("get"+Utils.capitalize(this.fieldNames[col]));
            return m.invoke(h);
        } catch(Exception e){
            Utils.showException(e);
        }
        return null;
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        if(this.dataValues.get(row)!=null){
            
            SGLog h=(this.dataValues.get(row));
            Class<? extends Object> cl=h.getClass();
            try{
                Method g=cl.getMethod("get"+Utils.capitalize(this.fieldNames[col]));
                Method m=cl.getMethod("set"+Utils.capitalize(this.fieldNames[col]),g.getReturnType());
                Class<? extends Object> ret=g.getReturnType();
                m.invoke(h, ret.cast(value));
            } catch(Exception e){
                Utils.showException(e);
            }
            fireTableCellUpdated(row, col);
	    fireTableRowsUpdated(row, row);
            this.panel.logModificato();
        }
    }    
    @Override
    public boolean isCellEditable(int row, int col){
        //if(row>0)return col==0||col>1;
        return true;
    }    
}
