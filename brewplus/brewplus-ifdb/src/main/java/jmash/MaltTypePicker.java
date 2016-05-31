/*
 * MaltTypePicker.java
 *
 * Created on 13 gennaio 2008, 11.50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package jmash;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Alessandro
 */
public class MaltTypePicker extends Picker {

    /** Creates a new instance of MaltTypePicker */
    public MaltTypePicker() {
        super(Gui.maltPickerTableModel);
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/malts.png")));
        setFilters(
                new String[]{"Tutti", "Grani", "Estratto secco", "Estratto liquido", "Cristalli", "Aggiuntivo", "Altro"},
                new ActionListener() {

            @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().compareTo("Tutti") == 0) {
                            Gui.maltPickerTableModel.setFilterOff();
                        } else {
                            Gui.maltPickerTableModel.setFilterOn(4, e.getActionCommand());
                        }
                    }
                });
        this.getTable().getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col) {
                return (JLabel) getTable().getValueAt(markedRow, 0);
            }
        });
    }
}
