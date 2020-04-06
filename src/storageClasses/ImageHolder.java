package storageClasses;

import processing.core.PImage;
import util.Util;
import processing.core.PApplet;

import java.awt.Color;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageHolder {
	private BufferedImage bI;
	private BufferedImage bufImag;
	private PApplet testPA;
	private String fileName;
	
	public ImageHolder(Long seed, String fN) {
		bI = new BufferedImage(500, 1000, BufferedImage.TYPE_INT_RGB);
		testPA = new PApplet();
		testPA.noiseSeed(seed);
		testPA.noiseDetail(5, 0.3f);
		fileName = fN;
		setColors();
		saveImage();
		bufImag = loadImage();
	}
	
	private BufferedImage loadImage() {
		BufferedImage bufImg = null;
		try {
			bufImg = ImageIO.read(new File("assets/" + fileName));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return bufImg;
	}
	
	private void saveImage() {
		try {
			File outputFile = new File("assets/" + fileName);
			ImageIO.write(bI, "png", outputFile);
		} catch (Exception e) {
			
		}
	}
	
	public BufferedImage getImage() {
		return bufImag;
	}
	
	public void getColors() {
		for (int x = 0; x < bufImag.getWidth(); x++) {
			for (int y = 0; y < bufImag.getHeight(); y++) {
			}
		}
	}
	
	private void setColors() {
		float xOff = 0;
		for (int x = 0; x < bI.getWidth(); x++) {
			float yOff = 0;
			for (int y = 0; y < bI.getHeight(); y++) {
				int red = (int)testPA.map(testPA.noise(xOff, yOff), 0, 1, 0, 255);
				//int blue = (int)testPA.map(testPA.noise(xOff, yOff), 0, 1, 0, 255);
				//int green = (int)testPA.map(testPA.noise(xOff, yOff), 0, 1, 0, 255);
				Color myColor = new Color(red, 0, 0);
				bI.setRGB(x, y, myColor.getRGB());
				yOff += 0.01;
			}
			xOff += 0.01;
		}
	}
}
