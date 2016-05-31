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

package jmash.component;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
public class JSpinnerEditor extends AbstractCellEditor implements TableCellEditor {
    /**
     *
     */
    private static final long serialVersionUID = 8292241453794062331L;
    final JComboBox spinner = new JComboBox();
    
    // Initializes the spinner.
    public JSpinnerEditor(String[] items) {
	for(int i=0;i<items.length;i++) {
	    this.spinner.addItem(items[i]);
	    //spinner.setModel(new SpinnerListModel(java.util.Arrays.asList(items)));
	}
    }
    
    // Prepares the spinner component and returns it.
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	//spinner.setValue(value);
	this.spinner.setSelectedItem(value);
	return this.spinner;
    }
    
    // Enables the editor only for double-clicks.
    @Override
    public boolean isCellEditable(EventObject evt) {
	if (evt instanceof MouseEvent) {
	    return ((MouseEvent)evt).getClickCount() >= 2;
	}
	return true;
    }
    
    // Returns the spinners current value.
    @Override
    public Object getCellEditorValue() {
	return this.spinner.getSelectedItem();
    }
}
