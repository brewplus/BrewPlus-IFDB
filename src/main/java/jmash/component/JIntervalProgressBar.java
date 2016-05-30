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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.BoundedRangeModel;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class JIntervalProgressBar extends JProgressBar {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3006652093933797277L;
	private BufferedImage theImage;
    
    private class CustomProgressBarUI extends BasicProgressBarUI {
        @Override
		protected void paintDeterminate(Graphics g, JComponent c) {
            if (!(g instanceof Graphics2D)) {
				return;
			}
            if (getTheImage() == null) {
     //           super.paintDeterminate(g, c);
   //             return;
            }
            Insets b = getInsets();
            int barRectWidth = getWidth() - (b.right + b.left);
            int barRectHeight = getHeight() - (b.top + b.bottom);
            int amountFull = getAmountFull(b, barRectWidth, barRectHeight);
            
            int lb=Math.round(barRectWidth * JIntervalProgressBar.this.lowerBound/getMaximum());
            int ub=Math.round(barRectWidth * JIntervalProgressBar.this.upperBound/getMaximum());
            
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(getForeground());
            if (getOrientation() == SwingConstants.HORIZONTAL) {
                if (c.getComponentOrientation().isLeftToRight()) {
                    fillRectangle(b.left+lb, b.top, /*amountFull +*/ b.left+(ub-lb), barRectHeight, g2);
                    g2.fillRect(b.left+amountFull-1,  b.top+barRectHeight/2-1, 3, 3);
                } else {
                    fillRectangle(barRectWidth + b.left - amountFull, b.top, amountFull, barRectHeight, g2);
                }
                
            } else {
                fillRectangle(b.left, b.top + barRectHeight - amountFull, barRectWidth, amountFull, g2);
            }
/*            if (progressBar.isStringPainted()) {
                paintString(g, b.left, b.top,
                        barRectWidth, barRectHeight,
                        amountFull, b);
            }*/
        }
        
        private void fillRectangle(int x, int y, int aWidth, int aHeight, Graphics2D aGraphics) {
            if (getTheImage() == null) {
                aGraphics.fillRect(x, y, aWidth, aHeight);
                return;
            }
            double scaleX = (double)aWidth / (double)getTheImage().getWidth();
            double scaleY = (double)aHeight / (double)getTheImage().getHeight();
            AffineTransform transform = AffineTransform.getScaleInstance(scaleX, scaleY);
            aGraphics.translate(x, y);
            aGraphics.drawImage(getTheImage(), transform, null);
            aGraphics.translate(-x, -y);
        }
    }
    
    public JIntervalProgressBar() {}
    
    public JIntervalProgressBar(int orient) {
        super(orient);
    }
    
    public JIntervalProgressBar(int min, int max) {
        super(min, max);
    }
    
    public JIntervalProgressBar(int orient, int min, int max) {
        super(orient, min, max);
    }
    
    public JIntervalProgressBar(BoundedRangeModel newModel) {
        super(newModel);
    }
    
    public void setImage(BufferedImage aBufferedImage) {
        setTheImage(aBufferedImage);
    }
    
    @Override
	public void setUI(ProgressBarUI ui) {
        super.setUI(new CustomProgressBarUI());
    }
    
    private int lowerBound;
    private int upperBound;
    
    public BufferedImage getTheImage() {
        return this.theImage;
    }
    
    public void setTheImage(BufferedImage theImage) {
        this.theImage = theImage;
    }
    
    public int getLowerBound() {
        return this.lowerBound;
    }
    
    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }
    
    public int getUpperBound() {
        return this.upperBound;
    }
    
    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
}