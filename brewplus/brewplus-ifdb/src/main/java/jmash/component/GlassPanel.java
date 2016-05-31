/*
 * GlassPanel.java
 *
 * Created on 26 dicembre 2008, 16.35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import jmash.Main;
import jmash.Ricetta;
import jmash.Utils;

/**
 *
 * @author Alessandro
 */
public class GlassPanel extends JPanel{
    //public static ImageIcon glassColorIcon = new ImageIcon( java.awt.Toolkit.getDefaultToolkit().createImage(Ricetta.class.getResource("/jmash/images/glass2.png")));
    private static Image glassColor = Main.glassColorIcon.getImage();
    
    private static BufferedImage colorSource = new BufferedImage(Ricetta.dimx, Ricetta.dimy, BufferedImage.TYPE_INT_ARGB);;
    
    private static final long serialVersionUID = -7721900896919471804L;
    BufferedImage doubleBuffer;
    BufferedImage  dest3;
    
    BufferedImage bwSource;
    double maxH=0;
    private Ricetta ricetta;
    public GlassPanel(Ricetta ricetta){
	this.ricetta=ricetta;
	this.doubleBuffer = new BufferedImage(Ricetta.dimx, Ricetta.dimy, BufferedImage.TYPE_INT_ARGB);
	this.dest3 = new BufferedImage(Ricetta.dimx, Ricetta.dimy, BufferedImage.TYPE_INT_ARGB);
	
	colorSource.createGraphics().drawImage(glassColor, 0, 0, this);
	this.setDoubleBuffered(true);
	for(int y=0;y<Ricetta.dimy;y++) {
	    for(int x=0;x<Ricetta.dimx;x++){
		int red= (colorSource.getRGB(x, y)>>16)&0xff;
		int green= (colorSource.getRGB(x, y)>>8)&0xff;
		int blue= (colorSource.getRGB(x, y)>>0)&0xff;
		if(red > blue*1.9){
		    float f[]=new float[3];
		    Color.RGBtoHSB((int)red,(int)green,(int)blue,f);
		    if(f[2]>maxH)maxH=f[2];
		}
	    }
	}
    }
    @Override
    public void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D)g;
	Graphics2D destG = this.dest3.createGraphics();
	if(this.backColor!=null){
	    destG.setColor(this.backColor);
	    destG.drawRect(0,0,Ricetta.dimx,Ricetta.dimy);
	}
	destG.drawImage(this.doubleBuffer, 0, 0, this);
	g2d.drawImage(this.dest3, 0, 0, this);
    }
    Color backColor=null;
    int treshold=230;
    private double srm;
    public  void setColor(double srm){
	if(this.srm==srm)return;
	this.srm=srm;
	ricetta.setEbc(Utils.srmToEbc(srm));
	Color color=Main.treeColor.srmToRgb(srm);
	this.backColor=ricetta.getBackground();
	int bc=backColor.getRGB();
	float f[]=new float[3];
	float f2[]=new float[3];
	double factor=1.5f;
	for(int y=0;y<Ricetta.dimy;y++) {
	    for(int x=0;x<Ricetta.dimx;x++){
		int c=colorSource.getRGB(x, y);
		double red= (c>>16)&0xff;
		double green= (c>>8)&0xff;
		double blue= (c>>0)&0xff;
		if(green>treshold && red>treshold && blue>treshold)
		    doubleBuffer.setRGB(x,y,bc);
		else if(green/2>red && green/2>blue)
		    doubleBuffer.setRGB(x,y,bc);
		else if(red > blue*1.9){
		    Color.RGBtoHSB((int)red,(int)green,(int)blue,f);
		    Color.RGBtoHSB(color.getRed(),color.getGreen(),color.getBlue(),f2);
		    int a = (((int)(f[1]*256))&0xFF)<<24;
		    int fin=Color.HSBtoRGB(f2[0],f2[1],(float)(f[2]*f2[2]/maxH));
		    doubleBuffer.setRGB(x,y,fin);
		}else doubleBuffer.setRGB(x,y,c);
	    }
	}
	this.updateUI();
    }
}