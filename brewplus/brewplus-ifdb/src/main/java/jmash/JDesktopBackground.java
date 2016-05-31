package jmash;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JDesktopPane;

public class JDesktopBackground extends JDesktopPane{
	
	private Image image;
	private BufferedImage scaledImage;
	private int cachedHeight = -1;
	private int cachedWidth = -1;
	public JDesktopBackground(Image image) {
	super();
	this.image = image;
	}
	
	protected void paintComponent(Graphics g) {
		if (cachedHeight != getHeight() && cachedWidth != getWidth())
		createScaledImage();
		g.drawImage(scaledImage, 0, 0, this);
		}
	
	private void createScaledImage() {
		cachedWidth = getWidth();
		cachedHeight = getHeight();
		if (cachedWidth > 0 && cachedHeight > 0) {
		scaledImage = new BufferedImage(cachedWidth, cachedHeight,
		BufferedImage.TYPE_INT_RGB);
		scaledImage.createGraphics().drawImage(
		image,
		AffineTransform.getScaleInstance(((float) cachedWidth)
		/ image.getWidth(this), ((float) cachedHeight)
		/ image.getHeight(this)), null);
		} else
		scaledImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		}
	
}