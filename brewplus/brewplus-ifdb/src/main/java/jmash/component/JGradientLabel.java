/*
 * JGradientLabel.java
 *
 * Created on 12 novembre 2008, 20.40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 *
 * @author Alessandro
 */
public class JGradientLabel extends JLabel {
    
    /** Creates a new instance of JGradientLabel */
    public JGradientLabel() {
    }
    protected void paintComponent( Graphics g ) {
//	if ( !isOpaque( ) ) {
//	    super.paintComponent( g );
//	    return;
//	}
	Graphics2D g2d = (Graphics2D)g;
	int w = getWidth( );
	int h = getHeight( );
	
// Paint a gradient from top to bottom
	GradientPaint gp = new GradientPaint(
		0, 0, Color.RED,
		w, 0, Color.WHITE );
	
	g2d.setPaint( gp );
	g2d.fillRect( 0, 0, w, h );
	
	//setOpaque( false );
	//super.paintComponent( g );
	//setOpaque( true );
    }
}
