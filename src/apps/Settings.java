package apps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PVector;
import storageClasses.*;
import util.*;
import abstractClasses.*;

public class Settings extends Application{
	private ArrayList<SettingMenu> overallSettings, settingsToPaint;
	private ArrayList<ArrayList<SettingMenu>> settingLevels;
	private double x = 0, y = 0;
	private PVector loc;
	private double settingBoxHeight = 40;
	private Path2D.Double back;
	private int wallpaperChosen;
	
	public Settings() {
		overallSettings = new ArrayList<SettingMenu>();
		settingsToPaint = new ArrayList<SettingMenu>();
		settingLevels = new ArrayList<ArrayList<SettingMenu>>();
		setUpSettings();
		
		loc = new PVector(0, (float)settingBoxHeight/2 + 80);
		for (int i = 0; i < overallSettings.size(); i++) {
			//System.out.println(overallSettings.get(i).getName());
		}
		x = 0;
		y = 0;
		back = new Path2D.Double();
		setUpBackButton();
	}
	
	private void setUpBackButton() {
		back.moveTo(30, 10);
		back.lineTo(30, 20);
		back.lineTo(20, 30);
		back.lineTo(30, 40);
		back.lineTo(30, 50);
		back.lineTo(10, 30);
		back.lineTo(30, 10);
	}
	
	private void setUpSettings() {
		Util.setUpMenus(overallSettings);
		for (SettingMenu i : overallSettings) {
			settingsToPaint.add(i);
		}
		settingLevels.add(overallSettings);
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform tx = g2.getTransform();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRect(0, 0, frameDim.width, frameDim.height);
		g2.translate(loc.x, loc.y);
		double tempY = loc.y;
		Font temp = g2.getFont().deriveFont(15f);
		g2.setFont(temp);
		settingsToPaint = settingLevels.get(settingLevels.size()-1);
		for (int i = 0; i < settingsToPaint.size(); i++) {
			settingsToPaint.get(i).draw(g2);
			g2.setColor(new Color(50, 50, 50));
			settingsToPaint.get(i).setXY(0, tempY);
			try {
				if (settingsToPaint.get(i).getGroup() == settingsToPaint.get(i+1).getGroup()) {
					g2.drawLine(40, (int)settingBoxHeight/2, frameDim.width, (int)settingBoxHeight/2);
					g2.translate(0, settingBoxHeight);
					tempY += settingBoxHeight;
				} else {
					g2.translate(0, settingBoxHeight * 1.75);
					tempY += settingBoxHeight*1.75;
				}
			} catch(Exception e) {
				g2.translate(0, settingBoxHeight);
			}

			
		}
		g2.setTransform(tx);
		tx = g2.getTransform();

		g2.setColor(Color.black);
		g2.fillRect(0, 0, frameDim.width, 70);
		temp = g2.getFont().deriveFont(24f);
		g2.setFont(temp);
		g2.setColor(Color.white);
		g2.drawString("Settings", frameDim.width/2-frameDim.width/10, 40);
		g2.fill(back);
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
		int location = -1;
		for (int i = 0; i < settingsToPaint.size(); i++) {
			if (settingsToPaint.get(i).clicked(e)) {
				location = i;
				break;
			}
		}
		if (location == -1) {
			AffineTransform af = new AffineTransform();
			Shape s = af.createTransformedShape(back);
			if (s.contains(e.getX(), e.getY())) {
				settingsToPaint.clear();
				try {
					settingLevels.remove(settingLevels.get(settingLevels.size()-1));
				} catch (IndexOutOfBoundsException ioobe) {

				}
			}
		}
		try {
			switch (settingsToPaint.get(location).getName()) {
			case "Photo 1":
				wallpaperChosen = 1;
				break;
			case "Photo 2":
				wallpaperChosen = 2;
				break;
			case "Photo 3":
				wallpaperChosen = 3;
				break;
			case "Keep Current":
				wallpaperChosen = 4;
				break;
			}
		} catch (IndexOutOfBoundsException ioobe) {
			
		}
		try {
		Iterator<SettingMenu> t = new settingIterator(settingsToPaint.get(location));
		t.next();
		} catch (IndexOutOfBoundsException ioobe) {
			
		}
		
		return temp;
	}
	
	private class settingIterator implements Iterator<SettingMenu> {
		private int index;
		private SettingMenu tlf;
		private ArrayList<SettingMenu> tempAL;
		private settingIterator(SettingMenu toLookFor) {
			index = 0;
			tlf = toLookFor;
			tempAL = new ArrayList<SettingMenu>();
			advance();
		}
		
		private void advance() {
			while (index < settingsToPaint.size()) {
				if (settingsToPaint.get(index) == tlf) {
					return;
				}
				index++;
			}
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index < settingsToPaint.size();
		}

		@Override
		public SettingMenu next() {
			SettingMenu t = settingsToPaint.get(index++);
			tempAL = t.getSubMenu();
			if (tempAL.size() != 0) {
			settingLevels.add(new ArrayList<SettingMenu>(tempAL));
			}
			advance();
			// TODO Auto-generated method stub
			return t;
		}
		
	}
	
	public int hasWPChanged() {
		return wallpaperChosen;
	}
}
