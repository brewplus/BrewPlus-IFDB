/*
 * JTableGravityEditor.java
 *
 * Created on 9 marzo 2008, 14.11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.component;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Alessandro
 */
public class JTableGravityEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
    private JGravitySpinner spinner=new JGravitySpinner();
    /** Creates a new instance of JTableGravityEditor */
    public JTableGravityEditor() {
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	//spinner.setValue(value);
	this.getSpinner().setGravity((Double)value);
	return this.getSpinner();
    }    
    @Override
    public Object getCellEditorValue() {
	return this.getSpinner().getGravity();
    }    
    @Override
    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
	getSpinner().setGravity((Double)value);
	return getSpinner();
    }

	public JGravitySpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JGravitySpinner spinner) {
		this.spinner = spinner;
	}
}
