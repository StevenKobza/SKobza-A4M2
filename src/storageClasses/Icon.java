/**
 * @author Steven Kobza
 * @version 1.0
 * <h1> Icon Storage Class </h1>
 * <p> This stores the icons that are used on the home screen </p>
 */

package storageClasses;

import java.awt.Color;
import java.awt.Graphics2D;
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
	private Color bGNonClick = new Color(27, 27, 27), fGNonClick = Color.white;
	private Color bGYesClick = new Color(200, 200, 200), fGYesClick = new Color(27, 27, 27);
	private Color bGColor, fGColor;
	
	public Icon(String name, double x, double y, double w, double h) {
		this.name = name;
		this.x = x;
		this.y = y;
		bGColor = bGNonClick;
		fGColor = fGNonClick;
		icon = new Rectangle2D.Float((float)-w/2, (float)-h/2, (float)w, (float)h);
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform af = g2.getTransform();
		g2.setColor(bGColor);
		g2.fill(icon);
		g2.setColor(fGColor);
		g2.drawString(name, -20, 0);
		g2.setTransform(af);
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
	
	public void setClicked() {
		bGColor = bGYesClick;
		fGColor = fGYesClick;
	}
	
	public void setUnClicked() {
		bGColor = bGNonClick;
		fGColor = fGNonClick;
	}
}
