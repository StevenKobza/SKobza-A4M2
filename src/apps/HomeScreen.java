package apps;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PVector;
import storageClasses.*;
import abstractClasses.*;

public class HomeScreen extends Application{
	private Calculator calc;
	private Canvas canv;
	private Photos photos;
	private Settings settings;
	private ArrayList<Icon> icons;
	private int appSelected = 0; //0 = Home Screen, 1 = Calc, 2 = Canv, 3 = Photos, 4 = Settings
	
	
	public HomeScreen(Dimension frameDim) {
		super(frameDim);
		calc = new Calculator();
		settings = new Settings();
		canv = new Canvas();
		icons = new ArrayList<Icon>();
		photos = new Photos();
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
		AffineTransform at = g2.getTransform();
		g2.setColor(Color.black);
		g2.drawString("When you go into an App, drag from bottom to go back", 100, 500);
		g2.setTransform(at);
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
	}
	
	public void drawSub(Graphics2D g2) {
		AffineTransform af = g2.getTransform();
		int tempY = frameDim.height/14;
		int tempX = frameDim.width/8;
		g2.translate(frameDim.width/8, frameDim.height/14);
		g2.setColor(Color.black);
		for (int i = 0; i < icons.size(); i++) {
			g2.setColor(Color.black);
			icons.get(i).setXY(tempX, tempY);
			g2.fill(icons.get(i).getIcon());
			g2.setColor(Color.white);
			g2.drawString(icons.get(i).getName(), -20, 0);
			g2.translate(frameDim.width/4, 0);
			tempX += frameDim.width/4;
		}
		g2.setTransform(af);
	}
	
	public void appToDisplay(int app) {
		appSelected = app;
	}
	
	public void scrollApp(double x, double y, double origin) {
		PVector temp = new PVector ((float)x, (float)y);
		switch(appSelected) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			//canv.scroll(temp);
			break;
		case 3:
			//photos.scroll(temp);
			break;
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
			break;
		}
	}
	
	@Override
	protected boolean clicked(MouseEvent e) {
		boolean temp = false;
		int pos = 0;
		for (int i = 0; i < icons.size(); i++) {
			if (icons.get(i).clicked(e)) {
				temp = true;
				pos = i+1;
				System.out.println(pos);
				break;
			}
		}
		appToDisplay(pos);
		return temp;
	}
}
