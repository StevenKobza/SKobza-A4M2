package apps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JLabel;

import storageClasses.*;
import abstractClasses.*;

public class Calculator extends Application{
	private ArrayList<NumberHolder> numbers;
	private RoundRectangle2D.Float zero;
	private static final int CIRCLE_BUTTON_DIA = 100;
	private static final int ZERO_BUTTON_HEIGHT = 100, ZERO_BUTTON_WIDTH = 220;
	private String enteredNums;
	private Color fontCol;
	private boolean tempBool;
	
	public Calculator() {
		numbers = new ArrayList<NumberHolder>();
		setUpAL();
		enteredNums = "";
		tempBool = false;
	}
	
	private void setUpAL() {
		String[] temp = {"C", "\u207A\u2215\u208B", "%", "\u2052", "7", "8", "9", "X", "4",
				"5", "6", "-", "1", "2", "3", "+", "0", ".", "="};
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == "0") {
				numbers.add(new NumberHolder(temp[i], 0, 0, ZERO_BUTTON_WIDTH, ZERO_BUTTON_HEIGHT, true));
			} else {
			NumberHolder tempnH = new NumberHolder(temp[i], 0, 0, CIRCLE_BUTTON_DIA, CIRCLE_BUTTON_DIA, false);
			numbers.add(tempnH);
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform tx = g2.getTransform();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.black);
		g2.fillRect(0,  0, frameDim.width, frameDim.height);
		double tempX = CIRCLE_BUTTON_DIA/1.6;
		double tempY = frameDim.height/3;
		g2.setColor(Color.white);
		g2.translate(CIRCLE_BUTTON_DIA/1.6, frameDim.height/3);
		Font savedFont = g2.getFont();
		g2.setFont(new Font("Calibri", Font.BOLD, 96));
		g2.drawString(enteredNums, -CIRCLE_BUTTON_DIA/2, -96);
		g2.setFont(savedFont.deriveFont(35f));
		boolean newLine = false;
		for (int i = 0; i < numbers.size(); i++) {
			newLine = false;
			if (i < 3) {
				g2.setColor(Color.gray);
				fontCol = Color.black;
			} else if (i == 3 || i == 7 || i == 11 || i == 15 || i == 18) {
				g2.setColor(Color.orange);
				fontCol = Color.white;
				newLine = true;
			} else if (i == 17) {
				g2.setColor(Color.DARK_GRAY);
				fontCol = Color.white;
				tempX += (CIRCLE_BUTTON_DIA*1.25);
				g2.translate((CIRCLE_BUTTON_DIA*1.25), 0);
			}
			else {
				g2.setColor(Color.darkGray);
				fontCol = Color.white;
			}
			numbers.get(i).setXY(tempX, tempY);
			if (numbers.get(i).getChar() == "0") {
				g2.fill(numbers.get(i).getZero());
			} else {
			g2.fill(numbers.get(i).getRound());
			}
			g2.setColor(fontCol);
			if (i != 1) {
				g2.drawString(numbers.get(i).getChar(), -11, 10);
			} else {
			g2.drawString(numbers.get(i).getChar(), -20, 7);
			}
			tempX += CIRCLE_BUTTON_DIA*1.25;
			g2.translate(CIRCLE_BUTTON_DIA * 1.25, 0);
			if (newLine) {
				tempX -= (CIRCLE_BUTTON_DIA*1.25)*4;
				tempY += CIRCLE_BUTTON_DIA*1.25;
				g2.translate(-((CIRCLE_BUTTON_DIA * 1.25)*4), CIRCLE_BUTTON_DIA*1.25);
			}
		}
		g2.setTransform(tx);
	}

	
	public boolean clicked(MouseEvent e) {
		boolean temp = false;
		int location = 0;
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i).clicked(e)) {
				temp = true;
				location = i;
				System.out.println("found");
				break;
			}
		}
		System.out.println(temp);
		if (temp) {
			try {
				
				int tempI = Integer.parseInt(numbers.get(location).getChar());
				enteredNums = enteredNums.concat(numbers.get(location).getChar());
			} catch(Exception eI) {
				System.out.println(eI.getMessage());
				if (numbers.get(location).getChar() == "C") {
					enteredNums = "";
				}
			}
			
		}
		return temp;
	}
}
