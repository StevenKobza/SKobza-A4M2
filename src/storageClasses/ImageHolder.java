/**
 * @author Steven Kobza
 * @version 1.1
 * <h1>Image Holder</h1>
 * <p>This class is to hold the creation of the photos in the photo
 * app as well as the wallpaper</p>
 * I used https://necessarydisorder.wordpress.com/ as a basis for the heart curve
 * and understanding how to implement it.
 * I also used this video https://www.youtube.com/watch?v=ZI1dmHv3MeM in order to
 * figure out how to do it in Processing/P5.js. I then converted it to Java for
 * my usage.
 */

package storageClasses;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import mainClasses.PhonePanel;
import processing.core.PApplet;

public class ImageHolder {
	private BufferedImage bI;
	private BufferedImage bufImag;
	private PApplet testPA;
	private String fileName;
	private Path2D.Double path;
	private final String fileExt = ".png";
	private int type; // 1 = Normal Perlin, 2 = Heart, 3 = Butterfly curve
	
	public ImageHolder(Long seed, String fN, int type) {
		bI = new BufferedImage(500, 1000, BufferedImage.TYPE_INT_RGB);
		testPA = new PApplet(); //Creating a new version of the PApplet from processing
		testPA.noiseSeed(seed); 
		testPA.noiseDetail(10, 0.5f); //This is the detail for the Perlin generation. 10 levels and 0.5f fall off
		path = new Path2D.Double();
		fileName = fN;
		this.type = type;
		setColors(this.type);
		saveImage(); // I save the image and then load it again for reliability's sake
		bufImag = loadImage();
	}
	
	private BufferedImage loadImage() {
		BufferedImage bufImg = null;
		try {
			bufImg = ImageIO.read(new File("assets" + System.getProperty("file.separator") + fileName + fileExt)); //The getProperty is for me to work with more different OS's
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return bufImg;
	}
	
	private void saveImage() {
		try {
			File outputFile = new File("assets" + System.getProperty("file.separator") + fileName + fileExt); //The getProperty is for me to work with different OS's
			ImageIO.write(bI, "png", outputFile);
		} catch (Exception e) {
		}
	}
	
	public BufferedImage getImage() {
		if (bufImag != null) {
			return bufImag; //As a backup, if the loading doesn't work, I just end up returning the actual created image.
		} else {
			return bI;
		}
	}
	
	private void setColors(int type) {
		double phase = 0;
		float noiseMax = 1f;
		float zoff = 0f;
		Graphics2D g2 = bI.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.translate(PhonePanel.PHONE_W/2, PhonePanel.PHONE_H/2);
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(0.05f));
		path.moveTo(0,0);
		switch (type) {
		case 1:
			float xOff = 0;
			for (int x = 0; x < bI.getWidth(); x++) {
				float yOff = 0;
				for (int y = 0; y < bI.getHeight(); y++) {
					int red = (int)PApplet.map(testPA.noise(xOff, yOff), 0, 1, 0, 255); //Maps noise from 0 to 1 to 0 to 255 to create the red noise
					Color myColor = new Color(red, 0, 0);
					bI.setRGB(x, y, myColor.getRGB());
					yOff += 0.01;
				}
				xOff += 0.01;
			}
			break;
		case 2:
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(1f));
			for (int i = 0; i < 60; i++) {
				for (double a = 0; a < Math.PI * 2; a += Math.toRadians(5)) { // Iterates through a Polar coordinates system.
					float xoff = PApplet.map((float) Math.cos(a + phase), -1, 1, 0, noiseMax); 
					float yoff = PApplet.map((float) Math.sin(a + phase), -1, 1, 0, noiseMax);
					float r = PApplet.map(testPA.noise(xoff, yoff, zoff), 0, 1, 100, PhonePanel.PHONE_H / 4); //Maps the same way as the last map but instead from 100 to height/4
					double x = r / 15 * 16 * Math.pow(Math.sin(a), 3);
					double y = r / 15
							* (-13 * Math.cos(a) + 5 * Math.cos(2 * a) + 2 * Math.cos(3 * a) + Math.cos(4 * a));
					//These two are the Parametric Equation for creating a heart.
					path.lineTo(x, y);
				}
			}
			path.moveTo(PhonePanel.PHONE_W/2, PhonePanel.PHONE_H/2);
			g2.draw(path);
			break;
		case 3:
			for (int i = 0; i < 60; i++) {
				for (double a = 0; a < Math.PI * 12; a += Math.toRadians(5)) {
					float xoff = PApplet.map((float) Math.cos(a + phase), -1, 1, 0, noiseMax);
					float yoff = PApplet.map((float) Math.sin(a + phase), -1, 1, 0, noiseMax);
					float r = PApplet.map(testPA.noise(xoff, yoff, zoff), 0, 1, 150, PhonePanel.PHONE_H / 3);
					double x = r / 3 * Math.sin(a)
							* (Math.pow(Math.E, Math.cos(a)) - 2 * Math.cos(4 * a) - Math.pow(Math.sin(a / 12), 5));
					double y = -r / 3 * Math.cos(a)
							* (Math.pow(Math.E, Math.cos(a)) - 2 * Math.cos(4 * a) - Math.pow(Math.sin(a / 12), 5));
					//These two are the Parametric Equation for a Butterfly curve
					path.lineTo(x, y);
				}
				phase += 0.003;
				zoff += 0.01;
			}
			path.moveTo(PhonePanel.PHONE_W/2, PhonePanel.PHONE_H/2);
			g2.draw(path);
			break;
		case 4:
			g2.setColor(Color.white);
			g2.fillRect(-PhonePanel.PHONE_W/2, -PhonePanel.PHONE_H/2, PhonePanel.PHONE_W, PhonePanel.PHONE_H);
			g2.setColor(Color.black);
			noiseMax = 0.2f;
			g2.setStroke(new BasicStroke(1f));
			for (int i = 0; i < 60; i++) {
				for (double a = 0; a < Math.PI * 2; a += Math.toRadians(5)) { // Iterates through a Polar coordinates system.
					float xoff = PApplet.map((float) Math.cos(a + phase), -1, 1, 0, noiseMax); 
					float yoff = PApplet.map((float) Math.sin(a + phase), -1, 1, 0, noiseMax);
					float r = PApplet.map(testPA.noise(xoff, yoff, zoff), 0, 1, 100, PhonePanel.PHONE_H / 4); //Maps the same way as the last map but instead from 100 to height/4
					double x = r / 15 * 16 * Math.pow(Math.sin(a), 3);
					double y = r / 15
							* (-13 * Math.cos(a) + 5 * Math.cos(2 * a) + 2 * Math.cos(3 * a) + Math.cos(4 * a));
					//These two are the Parametric Equation for creating a heart.
					path.lineTo(x, y);
				}
			}
			path.moveTo(PhonePanel.PHONE_W/2, PhonePanel.PHONE_H/2);
			g2.draw(path);
			g2.setColor(Color.red);
			g2.fill(path);
		}
	}
	
	public String getFN() {
		return fileName;
	}
}
