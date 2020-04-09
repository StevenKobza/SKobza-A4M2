/**
 * @author Steven Kobza
 * @version 1.0
 * <h1>Home Screen</h1>
 * <p>This is the home screen for the iPhone. It holds all the code to transition
 * between apps and also communicates between the JPanel and the actual apps
 * themselves.</p>
 */

package apps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import processing.core.PVector;
import storageClasses.*;
import abstractClasses.*;

public class HomeScreen extends Application implements ActionListener{
	private Calculator calc;
	private Canvas canv;
	private Photos photos;
	private Settings settings;
	private ArrayList<Icon> icons;
	private int appSelected = 0; //0 = Home Screen, 1 = Calc, 2 = Canv, 3 = Photos, 4 = Settings
	private ImageHolder wallpaper;
	private Timer t;
	
	public HomeScreen(Dimension frameDim) {
		super(frameDim);
		ArrayList<Application> apps;
		calc = new Calculator();
		settings = new Settings();
		canv = new Canvas();
		icons = new ArrayList<Icon>();
		photos = new Photos();
		apps = new ArrayList<Application>();
		apps.add(calc);
		apps.add(settings);
		apps.add(canv);
		apps.add(photos);
		wallpaper = new ImageHolder((long)2580894, "wallpaper", 4);
		images.add(wallpaper);
		wallpaperName = "wallpaper";
		t = new Timer(50, this);
		setIcons();
	}
	
	public void setIcons() {
		String[] names = {"Calc", "Canvas", "Photos", "Settings"};
		for (int i = 0; i < 4; i++) {
			Icon temp = new Icon(names[i], 0, 0, (frameDim.width/2)/4, (frameDim.width/2)/4);
			icons.add(temp);
		}
	}
	
	public void draw(Graphics2D g2) {
		switch(appSelected) {
		case 0:
			drawSub(g2);
			break;
		case 1:
			calc.draw(g2);
			break;
		case 2:
			canv.draw(g2);
			break;
		case 3:
			photos.draw(g2);
			break;
		case 4:
			settings.draw(g2);
			break;
		}
		drawBottomBar(g2);
	}
	
	public void drawBottomBar(Graphics2D g2) {
		AffineTransform af = g2.getTransform();
		g2.setColor(new Color(20, 20, 20, 50));
		g2.translate(0, frameDim.height-100);
		g2.fillRect(0, 0, frameDim.width, 100);
		g2.setColor(Color.white);
		g2.fillRect(frameDim.width/2-50, 20, 100, 10);
		g2.setTransform(af);
	}
	
	public void drawSub(Graphics2D g2) {
		AffineTransform af = g2.getTransform();
		int tempY = frameDim.height/14;
		int tempX = frameDim.width/8;
		g2.drawImage(wallpaper.getImage(), 0, 0, null);
		g2.translate(frameDim.width/8, frameDim.height/14);
		g2.setColor(Color.black);
		for (int i = 0; i < icons.size(); i++) {
			icons.get(i).setXY(tempX, tempY);
			icons.get(i).draw(g2);
			g2.translate(frameDim.width/4, 0);
			tempX += frameDim.width/4;
		}
		g2.setTransform(af);
	}
	
	public void appToDisplay(int app) {
		appSelected = app;
	}
	
	private void setWallpaper() {
		try {
			Iterator<ImageHolder> iI = new imageIterator(wallpaperName);
			wallpaper = iI.next();
		} catch (Exception e) {
			
		}
		if (wallpaper == null) {
			wallpaper = images.get(3);
		}
	}
	
	
	public void scrollApp(double x, double y, double origin) {
		PVector temp = new PVector ((float)x, (float)y);
		switch(appSelected) {
		case 4:
			settings.scroll(temp, origin);
			break;
		}
	}
	
	public void appClicked(MouseEvent e) {
		//calc.clicked(e);
		switch(appSelected) {
		case 0:
			this.clicked(e);
			break;
		case 1:
			calc.clicked(e);
			break;
		case 2:
			canv.clicked(e);
			break;
		case 3: 
			break;
		case 4:
			settings.clicked(e);
			setWallpaper();
			break;
		}
	}
	
	public void updatePhoto(KeyEvent e) {
		if (appSelected == 3 && (e.getKeyCode() == 39 || e.getKeyCode() == 37)) {
			//If Photos selected and left or right arrow key selected
			photos.update(e);
		} else if (e.getKeyCode() == 27) {
			//If ESC key pressed
			if (appSelected == 0) {
				System.exit(0);
			}
			appSelected = 0;
		}
	}
	
	@Override
	protected boolean clicked(MouseEvent e) {
		boolean temp = false;
		int pos = 0;
		for (int i = 0; i < icons.size(); i++) {
			if (icons.get(i).clicked(e)) {
				icons.get(i).setClicked();
				t.restart();
				temp = true;
				pos = i+1;
				break;
			}
		}
		appToDisplay(pos);
		return temp;
	}
	
	public int getAppSelected() {
		return appSelected;
	}
	
	private class imageIterator implements Iterator<ImageHolder> {
		private int index;
		private String tlf;
		
		private imageIterator(String toLookFor) {
			index = 0;
			tlf = toLookFor;
			advance();
		}
		
		private void advance() {
			while (index < images.size()) {
				if (images.get(index).getFN() == tlf) {
					return;
				}
				index++;
			}
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index < images.size();
		}

		@Override
		public ImageHolder next() {
			// TODO Auto-generated method stub
			ImageHolder t = images.get(index);
			advance();
			return t;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Icon i : icons) {
			i.setUnClicked();
		}
		
	}
}
