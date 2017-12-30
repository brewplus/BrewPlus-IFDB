/*
 * MaltTypePicker.java
 *
 * Created on 13 gennaio 2008, 11.50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package jmash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author DiegoDellaBotte
 */
public class HopTypePicker extends Picker {

	/** Creates a new instance of MaltTypePicker */
	public HopTypePicker() {
		super(Gui.hopPickerTableModel);
		setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/hops.png")));
		setFilters(new String[] { "Tutti", "Amaro", "Aroma", "Amaro/Aroma", "Altro" }, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getActionCommand().compareTo("Tutti") == 0) {
							Gui.hopPickerTableModel.setFilterOff();
						} else {
							Gui.hopPickerTableModel.setFilterOn(4, e.getActionCommand());
						}
					}
				});
		
		/*this.getTable().getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected,
					boolean hasFocus, int markedRow, int col) {
				return (JLabel) getTable().getValueAt(markedRow, 0);
			}
		});*/
	}
}
