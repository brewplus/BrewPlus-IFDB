/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmash.component;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author a.cerella
 */
public class CheckBoxModelListener implements TableModelListener {

    @Override
    public void tableChanged(TableModelEvent e) {
       int row = e.getFirstRow();
       int column = e.getColumn();
       if (column == 6) {
                DefaultTableModel model = (DefaultTableModel) e.getSource();
                Boolean checked = (Boolean) model.getValueAt(row, column);
                if (checked) {
                    if ((Double)model.getValueAt(row, 5) < 0)
                        model.setValueAt(0.0, row, 5);
                } else {
                    model.setValueAt((Double)model.getValueAt(row, 4) - (Double)model.getValueAt(row, 3), row, 5);
                }
            }
    }
    
}
