package apps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import abstractClasses.Application;
import storageClasses.ImageHolder;

public class Photos extends Application {
	private ArrayList<ImageHolder> photos;
	private int photoToShow;
	
	public Photos() {
		photos = new ArrayList<ImageHolder>();
		photoToShow = 0;
		setUpAL();
	}
	@Override
	protected boolean clicked(MouseEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawImage(photos.get(photoToShow).getImage(), 0, 0, null);
		g2.setColor(new Color(15, 15, 15, 85));
		g2.fillRect(0, 0, frameDim.width, 50);
		g2.setColor(Color.white);
		Font temp = g2.getFont().deriveFont(24f);
		g2.setFont(temp);
		g2.drawString("Photo: " + (int)(photoToShow+1) + " of " + photos.size(), frameDim.width/2 - frameDim.width/6, 40);
		g2.setTransform(at);
		
	}
	
	private void setUpAL() {
		photos.add(new ImageHolder((long) 1943058, "PhotoOne.png"));
		photos.add(new ImageHolder((long) 7709057, "PhotoTwo.png"));
		photos.add(new ImageHolder((long) 3636480, "PhotoThree.png"));
	}
	
	public void update(KeyEvent e) {
		if (e.getKeyCode() == 39 && photoToShow < photos.size() - 1) {
			photoToShow++;
		} else if (e.getKeyCode() == 37 && photoToShow > 0) {
			photoToShow--;
		}
	}
	
	public ArrayList<ImageHolder> getImages() {
		return photos;
	}
}
