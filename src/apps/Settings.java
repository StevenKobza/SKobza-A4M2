package apps;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PVector;
import storageClasses.*;
import util.*;
import abstractClasses.*;

public class Settings extends Application{
	private ArrayList<SettingMenu> settings;
	private double x = 0, y = 0;
	private PVector loc;
	private double settingBoxHeight = 40;
	public Settings() {
		settings = new ArrayList<SettingMenu>();
		setUpSettings();
		loc = new PVector(0, (float)settingBoxHeight/2 + 80);
		for (int i = 0; i < settings.size(); i++) {
			System.out.println(settings.get(i).getName());
		}
		x = 0;
		y = 0;
	}
	
	private void setUpSettings() {
		Util.setUpMenus(settings);
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform tx = g2.getTransform();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRect(0, 0, frameDim.width, frameDim.height);
		g2.translate(loc.x, loc.y);
		double tempY = loc.y;
		for (int i = 0; i < settings.size(); i++) {
			settings.get(i).draw(g2);
			g2.setColor(new Color(50, 50, 50));
			settings.get(i).setXY(0, tempY);
			try {
				if (settings.get(i).getGroup() == settings.get(i+1).getGroup()) {
					g2.drawLine(40, (int)settingBoxHeight/2, frameDim.width, (int)settingBoxHeight/2);
					g2.translate(0, settingBoxHeight);
					tempY += settingBoxHeight;
				} else {
					g2.translate(0, settingBoxHeight * 1.75);
					tempY += settingBoxHeight*1.75;
				}
			} catch(Exception e) {
				g2.translate(0, settingBoxHeight);
				System.out.println(e.getMessage());
			}

			
		}
		g2.setTransform(tx);
	}
	
	public void scroll(PVector dist, double origin) {
		PVector temp = new PVector (0, (float)origin);
		PVector dir = PVector.sub(temp, dist);
		dir.normalize();
		dir.mult(2f);
		dir.limit(10f);
		loc.add(dir);
		if (loc.y > 100) {
			loc.y = 100;
		} else if (loc.y < -1000) {
			loc.y = -1000;
		}
	}
	
	public boolean clicked(MouseEvent e) {
		boolean temp = false;
		int location = 0;
		for (int i = 0; i < settings.size(); i++) {
			if (settings.get(i).clicked(e)) {
				location = i;
				System.out.println("found");
				break;
			}
		}
		System.out.println(settings.get(location).getName());
		return temp;
	}
}
