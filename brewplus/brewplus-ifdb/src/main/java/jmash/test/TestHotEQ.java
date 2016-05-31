/*
 * TestHotEQ.java
 *
 * Created on 3 febbraio 2008, 11.05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.test;

import atp.cHotEqn;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Alessandro
 */
public class TestHotEQ  extends ApplicationFrame{
    
    /** Creates a new instance of TestHotEQ */
    public TestHotEQ(String s) {
	super(s);
    }
    private static cHotEqn viewer=new cHotEqn();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	TestHotEQ demo=new TestHotEQ("Test");
	
	JPanel pnl=new JPanel();
	pnl.setLayout(new BorderLayout());
	pnl.add(viewer, BorderLayout.CENTER);
	demo.setContentPane(pnl);
	demo.pack();
	
	String eq="3.5*x+\\frac{5}{x+y}+4";
	viewer.setEquation(eq);
	demo.setVisible(true);
    }
    
}
