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

import java.util.Date;
import java.util.List;
import jmash.*;

/**
 *
 * @author Alessandro
 */
public abstract class PickerTableModel extends javax.swing.table.DefaultTableModel {
    
    /** Creates a new instance of PickerTableModel */
    public PickerTableModel() {
    }
    public abstract void emptyRows();
    public abstract List<? extends Object> getRows();
    @Override
	public boolean isCellEditable(int row, int col) {
        return false; }
    @Override
	public void setValueAt(Object value, int row, int col) {
    }
    
    private long t=0;
    private String type="";
    public int keyTyped(char c, Date d, int col, TableSorter sorter ){
        if(d.getTime()-this.t<500){
            this.type+=c;
        } else {
			this.type = ""+c;
		}
        this.t=d.getTime();
        

        for(int i=0;i<getRows().size();i++){
            if(!(this.getValueAt(sorter.modelIndex(i),col) instanceof String)) {
				return -1;
			}
            String str=(String)this.getValueAt(sorter.modelIndex(i),col);
            if(str.toLowerCase().startsWith(this.type.toLowerCase())){
                return i;
            }
        }
        return -1;
    }
    @Override
	public Class<? extends Object> getColumnClass(int c) {
        if(getValueAt(0, c)!=null) {
			return getValueAt(0, c).getClass();
		}
        return Object.class;
    }
    

}
