/*
 * JTableGravityRenderer.java
 *
 * Created on 9 marzo 2008, 14.01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.component;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Alessandro
 */
public class JTableGravityRenderer implements TableCellRenderer{
    
    JTableGravityEditor ed;
    public JTableGravityRenderer(JTableGravityEditor ed){
	this.ed=ed;
    }
    JTextField box=new JTextField();
    JGravitySpinner spin=new JGravitySpinner();
    @Override
    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){


	return box;
    }    
}
