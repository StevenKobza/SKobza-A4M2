package apps;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import abstractClasses.Application;
import storageClasses.ImageHolder;

public class Photos extends Application {
	private ArrayList<ImageHolder> photos;
	public Photos() {
		photos = new ArrayList<ImageHolder>();
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
		g2.drawImage(photos.get(2).getImage(), 0, 0, null);
		g2.setTransform(at);
		
	}
	
	private void setUpAL() {
		photos.add(new ImageHolder((long) 1943058, "PhotoOne.png"));
		photos.add(new ImageHolder((long) 7709057, "PhotoTwo.png"));
		photos.add(new ImageHolder((long) 3636480, "PhotoThree.png"));
	}
}
