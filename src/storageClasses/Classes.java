package storageClasses;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import subClasses.Module;

public class Classes {
	private double x, y;
	private String className;
	private String shortName;
	private ArrayList<Module> modules;
	private Color classColor;
	private RoundRectangle2D.Float background;
	private RoundRectangle2D.Float foreground;
	
	public Classes(String cN, String sN, Color classColor, double w, double h) {
		className = cN;
		shortName = sN;
		this.classColor = classColor;
		background = new RoundRectangle2D.Float((float)-w/2, (float)-h, (float)w, (float)(2*h), 15f, 15f);
		foreground = new RoundRectangle2D.Float((float)-w/2, (float)0, (float)w, (float)h, 15f, 15f);
	}
	
	public boolean clicked(MouseEvent e) {
		boolean temp = false;
		AffineTransform af = new AffineTransform();
		af.translate(x,y);
		Shape s = af.createTransformedShape(background);
		Shape sF = af.createTransformedShape(foreground);
		if (s.contains(e.getX(), e.getY()) || sF.contains(e.getX(), e.getY())){
			temp = true;
		}
		return temp;
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
