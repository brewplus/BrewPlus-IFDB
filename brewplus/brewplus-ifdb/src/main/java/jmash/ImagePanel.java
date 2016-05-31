/*
 * ImagePanel.java
 *
 * Created on 27 dicembre 2006, 21.59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Alessandro
 */
public class ImagePanel extends JPanel {
    
        /**
	 * 
	 */
	private static final long serialVersionUID = -570251782798081404L;

		public ImagePanel(Image backgroundImage){
            this.backgroundImage=backgroundImage;
        }
	protected Image backgroundImage;
 
	/* override the paint method to take into account
	   your background image */
	@Override
	public void paint (Graphics g)
	{
		// ease image tag
		Image i = this.backgroundImage;
		/* get image height/width (can send non-null
		   ImageObservers if you'd rather... */
		//int h = i.getHeight(null), w = i.getWidth(null);
		/* get background from JPanel to fill clear
			pixels in the image */
		java.awt.Color c = this.getBackground();
		// draw the background image to the Graphics
		// again, pass a non-null ImageObserver if you like
                
                g.drawImage(i.getScaledInstance(this.getWidth(),this.getHeight(), 0), 0, 0, c, null);
 
		/* now that your background is drawn, call the
		   JPanel default paint method which will paint all
		   its components (over your newly painted background) */
		super.paint(g);
	}
 
    
}
