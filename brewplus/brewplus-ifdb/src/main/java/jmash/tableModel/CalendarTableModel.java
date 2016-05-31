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

/**
 *
 * @author Alessandro
 */
public class CalendarTableModel {}/*extends GenericTableModel<CalendarEntry>{
    

    private static final long serialVersionUID = 6679052873627182022L;
    public CalendarTableModel() {
	columnNames = new String[]{ "Data", "Descrizione", ""};
	this.ret.setIcon(Main.clockIcon);
    }
    
    private JButton ret=new JButton();
    private JButton ret2=new JButton(Main.hopIcon);
    private JButton openFile=new JButton(
	    new ImageIcon(
	    getClass().getResource("/jmash/images/fileopen.png")));
    @Override
    public Object getValueAt(int row, int col) {
	CalendarEntry h=getRow(row);
	if(h!=null){
	    switch(col){
		case 0:
		    return h.getDescription();
		case 1:
		    return h.getDes();
		case 2:
		    return this.openFile;
	    }
	}
	return null;
    }
    
    
    @Override
    public boolean isCellEditable(int row, int col){
	return col!=2 ;
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
	if(this.dataValues.get(row)!=null){
	    
	    CalendarEntry h=(this.dataValues.get(row));
	    
	    switch(col){
		case 1:
		    h.setDes((String)value);
		    break;
		default:
		    break;
	    }
	    fireTableCellUpdated(row, col);
	}
    }
    
}*/