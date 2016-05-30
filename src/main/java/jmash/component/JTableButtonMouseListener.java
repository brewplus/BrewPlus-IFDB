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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;
import jmash.Ricetta;

public class JTableButtonMouseListener implements MouseListener {
    private JTable __table;
    private Ricetta ricetta;
    public JTableButtonMouseListener(JTable table,Ricetta ricetta) {
	this(table);
	this.ricetta=ricetta;
    }
    
    private void __forwardEventToButton(MouseEvent e) {
	TableColumnModel columnModel = this.__table.getColumnModel();
	int column = columnModel.getColumnIndexAtX(e.getX());
	int row    = e.getY() / this.__table.getRowHeight();
	Object value;
	JButton button;
	MouseEvent buttonEvent;
	
	if((row >= this.__table.getRowCount()) || (row < 0) ||
		(column >= this.__table.getColumnCount()) || (column < 0)) {
	    return;
	}
	
	value = this.__table.getValueAt(row, column);
	
	if(!(value instanceof JButton)) {
	    return;
	}
	
	button = (JButton)value;
	
	//System.out.println("mouse clicked, row ="+row);
	if(this.ricetta!=null) {
	    //System.out.println(" this.ricetta.hopPopup.setVisible "+row);
	    //this.ricetta.hopPopup.setVisible(true);
	    //System.out.println(" this.ricetta.hopPopup.show "+row);
	    this.ricetta.hopPopup.show(this.__table,e.getX(),e.getY());
	}
	buttonEvent =
		SwingUtilities.convertMouseEvent(this.__table, e, button);
	button.dispatchEvent(buttonEvent);
	// This is necessary so that when a button is pressed and released
	// it gets rendered properly.  Otherwise, the button may still appear
	// pressed down when it has been released.
	this.__table.repaint();
    }
    
    public JTableButtonMouseListener(JTable table) {
	this.__table = table;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
	
	__forwardEventToButton(e);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
	//__forwardEventToButton(e);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
	//__forwardEventToButton(e);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
	//__forwardEventToButton(e);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
	//__forwardEventToButton(e);
    }
}