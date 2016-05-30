/*
 * UpDownPopupMenu.java
 *
 * Created on 8 dicembre 2008, 11.05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.component;

import javax.swing.JPopupMenu;

/**
 *
 * @author Alessandro
 */
public class UpDownPopupMenu extends JPopupMenu {
    
    javax.swing.JMenuItem mnuItemUp=new javax.swing.JMenuItem("Su");
    javax.swing.JMenuItem mnuItemDown=new javax.swing.JMenuItem("Giù");
    
    public enum Action {UP, DOWN};
    
    public UpDownPopupMenu() {
	add(mnuItemUp);
	add(mnuItemDown);
	
	mnuItemUp.addActionListener(new java.awt.event.ActionListener() {
            @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		action=Action.UP;
	    }
	});
	mnuItemDown.addActionListener(new java.awt.event.ActionListener() {
            @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		action=Action.DOWN;
	    }
	});
    }
    private Action action;
    public Action getAction(){
	return action;
    }
}
