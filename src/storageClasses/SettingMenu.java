package storageClasses;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class SettingMenu {
	ArrayList<SettingMenu> subSettings;
	private String settingName;
	private int group;
	private double x, y;
	private Rectangle2D.Float box;
	public SettingMenu(String title, int group) {
		subSettings = new ArrayList<SettingMenu>();
		this.settingName = title;
		this.group = group;
		box = new Rectangle2D.Float(0, -20, 500, 40);
	}
	
	public void addSubSetting(SettingMenu subSet) {
		subSettings.add(subSet);
	}
	
	public String getName() {
		return settingName;
	}
	public int getGroup() {
		return group;
	}
	public void draw(Graphics2D g2) {
		g2.setColor(new Color(27, 27, 27));
		g2.fill(box);
		g2.setColor(Color.white);
		g2.drawString(settingName, 40, 4);
	}
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean clicked(MouseEvent e) {
		boolean temp = false;
		AffineTransform af = new AffineTransform();
		af.translate(x, y);
		Shape s = af.createTransformedShape(box);
		if (s.contains(e.getX(), e.getY())) {
			temp = true;
		}
		return temp;
	}
}
