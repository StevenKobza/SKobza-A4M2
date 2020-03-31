package storageClasses;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

public class Icon {
	String name;
	private double x;
	private double y;
	private Rectangle2D.Float icon;
	
	public Icon(String name, double x, double y, double w, double h) {
		this.name = name;
		this.x = x;
		this.y = y;
		icon = new Rectangle2D.Float((float)-w/2, (float)-h/2, (float)w, (float)h);
	}
	
	public boolean clicked(MouseEvent e) {
		boolean temp = false;
		AffineTransform af = new AffineTransform();
		af.translate(x, y);
		Shape s = af.createTransformedShape(icon);
		if (s.contains(e.getX(), e.getY())) {
			temp = true;
		}
		return temp;
	}
	
	public Rectangle2D.Float getIcon() {
		return icon;
	}
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String getName() {
		return name;
	}
}
