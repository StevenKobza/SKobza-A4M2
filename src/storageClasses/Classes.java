/**
 * @author Steven Kobza
 * @version 1.0
 * <h1> Storage Class for Classes </h1>
 * <p> This class just stores the information for different classes </p>
 */

package storageClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;


public class Classes {
	private double x, y;
	private String className;
	private String shortName;
	private Color classColor;
	private RoundRectangle2D.Float background;
	private RoundRectangle2D.Float foreground;
	private RoundRectangle2D.Float bGDrop;
	private RoundRectangle2D.Float fGDrop;
	private Area classArea;
	
	public Classes(String cN, String sN, Color classColor, double w, double h) {
		className = cN;
		shortName = sN;
		this.classColor = classColor;
		background = new RoundRectangle2D.Float((float)-w/2, (float)-h, (float)w, (float)(2*h), 15f, 15f);
		foreground = new RoundRectangle2D.Float((float)-w/2, (float)0, (float)w, (float)h, 15f, 15f);
		bGDrop = new RoundRectangle2D.Float((float)-w/2, (float)-h, (float)(w*1.05), (float)((h)), 15f, 15f);
		fGDrop = new RoundRectangle2D.Float((float)-w/2, (float)0, (float)(w*1.05), (float)((h)*1.05), 15f, 15f);
		classArea = new Area();
		classArea.add(new Area(background));
		classArea.add(new Area(foreground));
		classArea.add(new Area(bGDrop));
		classArea.add(new Area(fGDrop));
	}
	
	public boolean clicked(MouseEvent e) {
		boolean temp = false;
		AffineTransform af = new AffineTransform();
		af.translate(x,y);
		Shape s = af.createTransformedShape(classArea);
		if (s.contains(e.getX(), e.getY())){
			temp = true;
		}
		return temp;
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform af = g2.getTransform();
		g2.setColor(new Color(0, 0, 0, 25));
		g2.fill(bGDrop);
		g2.fill(fGDrop);
		g2.setColor(classColor);
		g2.fill(background);
		g2.setColor(Color.white);
		g2.fill(foreground);
		g2.setColor(Color.black);
		g2.setFont(g2.getFont().deriveFont(23f));
		g2.drawString(shortName, (int)(-95), 20);
		g2.setFont(g2.getFont().deriveFont(13f));
		g2.drawString(className, -95, 40);
		g2.setTransform(af);
	}
	
	public RoundRectangle2D.Float getBG() {
		return background;
	}
	
	public RoundRectangle2D.Float getFG() {
		return foreground;
	}
	
	public Color getClassColor() {
		return classColor;
	}
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getShortName() {
		return shortName;
	}
}
