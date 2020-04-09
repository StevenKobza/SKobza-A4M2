/**
 * @author Steven Kobza
 * @version 1.0
 * <h1> Canvas Application </h1>
 * <p> This is a version of the canvas app but a lot shittier because I don't have access to the niceties
 * of android studio or xcode to make my buttons look better.</p>
 */

package apps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import storageClasses.*;
import abstractClasses.*;
import util.Util;

public class Canvas extends Application{
	ArrayList<Classes> classes;
	public Canvas() {
		classes = new ArrayList<Classes> ();
		Util.setUpClasses(classes);
	}
	
	
	
	@Override
	protected boolean clicked(MouseEvent e) {
		// TODO Auto-generated method stub
		boolean temp = false;
		int pos = 0;
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).clicked(e)) {
				temp = true;
				pos = i;
				System.out.println(classes.get(i).getClassName());
				break;
			}
		}
		return temp;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		double tempX = frameDim.width/4;
		double tempY = 200;
		AffineTransform tx = g2.getTransform();
		g2.setColor(Color.gray);
		g2.fillRect(0, 0, frameDim.width, frameDim.height);
		g2.translate(frameDim.width/4, 200);
		for (int i = 0; i < classes.size(); i++) {
			classes.get(i).setXY(tempX, tempY);
			classes.get(i).draw(g2);
			if (i % 2 != 0) {
				g2.translate(-frameDim.width/2, frameDim.height/4);
				tempX -= frameDim.width/2;
				tempY += frameDim.height/4;
			} else {
				tempX += frameDim.width/2;
				g2.translate(frameDim.width/2, 0);
			}
		}
		g2.setTransform(tx);
	}
}
