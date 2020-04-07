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
import javax.swing.JOptionPane;

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
	private ArrayList<String> calculation; 
	private boolean add, sub, div, mult;
	
	public Calculator() {
		numbers = new ArrayList<NumberHolder>();
		setUpAL();
		enteredNums = "";
		tempBool = false;
		calculation = new ArrayList<String>();
	}
	
	private void setUpAL() {
		String[] temp = {"C", "\u207A\u2215\u208B", "%", "/", "7", "8", "9", "X", "4",
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
				if (numbers.get(i).getActive()) {
					g2.setColor(Color.lightGray);
				} else {
					g2.setColor(Color.gray);
				}
				fontCol = Color.black;
			} else if (i == 3 || i == 7 || i == 11 || i == 15 || i == 18) {
				if (numbers.get(i).getActive()) {
					g2.setColor(Color.white);
					fontCol = Color.orange;
				} else {
					g2.setColor(Color.orange);
					fontCol = Color.white;
				}
				newLine = true;
			} else if (i == 17) {
				if (numbers.get(i).getActive()) {
					g2.setColor(Color.lightGray);
				} else {
				g2.setColor(Color.DARK_GRAY);
				}
				fontCol = Color.white;
				tempX += (CIRCLE_BUTTON_DIA*1.25);
				g2.translate((CIRCLE_BUTTON_DIA*1.25), 0);
			}
			else {
				if (numbers.get(i).getActive()) {
					g2.setColor(Color.lightGray);
				} else {
				g2.setColor(Color.DARK_GRAY);
				}
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
		resetNums();
		g2.setTransform(tx);
	}

	public void resetNums() {
		for (NumberHolder i : numbers) {
			try {
				int tempI = Integer.parseInt(i.getChar());
				i.setActive(false);
			} catch (Exception e) {
				if (i.getChar() == "???") {
					i.setActive(false);
				} else if (i.getChar() == "C") {
					i.setActive(false);
				} else if (i.getChar() == "%") {
					i.setActive(false);
				}
			}
			
		}
	}
	
	public boolean clicked(MouseEvent e) {
		boolean temp = false;
		int location = 0;
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i).clicked(e)) {
				temp = true;
				location = i;
				System.out.println("found");
				numbers.get(i).setActive(true);
				break;
			}
		}
		System.out.println(temp);
		if (temp) {
			try {
				
				int tempI = Integer.parseInt(numbers.get(location).getChar());
				enteredNums = enteredNums.concat(numbers.get(location).getChar());
			} catch(Exception eI) {
				System.out.println(numbers.get(location).getChar());
				if (numbers.get(location).getChar() == "C") {
					for (NumberHolder i : numbers) {
						i.setActive(false);
					}
					enteredNums = "";
					add = false;
					sub = false;
					mult = false;
					div = false;
					calculation.clear();
				} else if (numbers.get(location).getChar() == "X") {
					for (NumberHolder i : numbers) {
						i.setActive(false);
					}
					add = false;
					sub = false;
					div = false;
					mult = true;
					numbers.get(location).setActive(true);
					calculation.add(enteredNums);
					enteredNums = "";
				} else if (numbers.get(location).getChar() == "=") {
					for (NumberHolder i : numbers) {
						i.setActive(false);
					}
					numbers.get(location).setActive(true);
					if (!calculation.isEmpty()) {
						double tempD = 0;
						try {
							tempD = Integer.parseInt(calculation.get(0));
						} catch (NumberFormatException n) {
							if (!calculation.get(0).isEmpty()) {
								tempD = Double.parseDouble(calculation.get(0));
							} else {
								tempD = 0;
							}
						}
						double tempEntered = Integer.parseInt(enteredNums);
						if (add) {
							enteredNums = Double.toString(tempD + tempEntered);
						} else if (sub) {
							enteredNums = Double.toString(tempD - tempEntered);
						} else if (mult) {
							enteredNums = Double.toString(tempD * tempEntered);
						} else if (div) {
							try {
								if (tempEntered == 0) {
									throw new IllegalArgumentException("Divisor Cannot Be Zero");
								}
							enteredNums = Double.toString(tempD / tempEntered);
							} catch (Exception nE) {
								JOptionPane.showMessageDialog(null, "Cannot Divide By 0", "Ur Rekt M8", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					calculation.clear();
				} else if (numbers.get(location).getChar() == "/") {
					for (NumberHolder i : numbers) {
						i.setActive(false);
					}
					add = false;
					sub = false;
					mult = false;
					div = true;
					numbers.get(location).setActive(true);
					calculation.add(enteredNums);
					enteredNums = "";
				} else if (numbers.get(location).getChar() == "-") {
					for (NumberHolder i : numbers) {
						i.setActive(false);
					}
					add = false;
					mult = false;
					div = false;
					sub = true;
					numbers.get(location).setActive(true);
					calculation.add(enteredNums);
					enteredNums = "";
				} else if (numbers.get(location).getChar() == "+") {
					for (NumberHolder i : numbers) {
						i.setActive(false);
					}
					numbers.get(location).setActive(true);
					sub = false;
					mult = false;
					div = false;
					add = true;
					calculation.add(enteredNums);
					enteredNums = "";
				}
			}
			
		}
		return temp;
	}
}
