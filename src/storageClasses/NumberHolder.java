/**
 * @author Steven Kobza
 * @version 1.0
 * <h1> Storage Class for Numbers in Calc </h1>
 * <p> This class stores the buttons / numbers from the calculator </p>
 */

package storageClasses;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Float;

public class NumberHolder {
	private double x, y;
	private Ellipse2D.Float round;
	private RoundRectangle2D.Float zeroRound;
	private String character;
	private Boolean isActive;
	
	public NumberHolder(String c, double x, double y, float w, float h, boolean z) {
		this.x = x;
		this.y = y;
		round = new Ellipse2D.Float(-w/2, -h/2, w, h);
		if (z) {
		zeroRound = new RoundRectangle2D.Float(-w/5, -h/2, w, h, 100, 200);
		}
		character = c;
		isActive = false;
	}
	
	public boolean clicked(MouseEvent e) {
		boolean temp = false;
		AffineTransform af = new AffineTransform();
		af.translate(x, y);
		Shape s = af.createTransformedShape(round);
		if (s.contains(e.getX(), e.getY())) {
			temp = true;
		}
		return temp;
	}
	
	public Ellipse2D.Float getRound() {
		return round;
	}
	
	public String getChar() {
		return character;
	}
	
	public void setChar(String c) {
		character = c;
	}
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public RoundRectangle2D.Float getZero() {
		return zeroRound;
	}
	
	public void setActive(Boolean t) {
		isActive = t;
	}
	
	public Boolean getActive() {
		return isActive;
	}
}
