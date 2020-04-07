package mainClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.Timer;

import apps.*;

@SuppressWarnings("serial")
public class PhonePanel extends JPanel implements ActionListener{
	private static final int PHONE_W = 500;
	private static final int PHONE_H = 1000;
	private double lastIntYPos;
	private Timer t;
	private int appSelected = 0; //0 = Home Screen, 1 = Calc, 2 = Canv, 3 = Photos, 4 = Settings
	HomeScreen homeScreen;
	private boolean scrolling = false, introScreen = true;
	
	public PhonePanel(int fr) {
		super();
		this.setPreferredSize(new Dimension(PHONE_W+50, PHONE_H));
		homeScreen = new HomeScreen(new Dimension(PHONE_W, PHONE_H));
		appSelected = 4;
		homeScreen.appToDisplay(0);
		t = new Timer(1000/fr, this);
		t.restart();
		
		lastIntYPos = 0;
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				homeScreen.updatePhoto(e);
				repaint();
			}
			public void keyTyped(KeyEvent e) {
			}
		});
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				lastIntYPos = 0;
				scrolling = false;
			}
			public void mousePressed(MouseEvent e) {
				lastIntYPos = e.getY();
			}
			public void mouseClicked(MouseEvent e) {
				if (introScreen == true) {
					introScreen = false;
					repaint();
				} else {
					homeScreen.appClicked(e);
					repaint();
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			double scrollAmount;
			public void mouseDragged(MouseEvent e) {
				//scrollAmount = e.getY() - lastIntYPos;
				if (e.getY() > PHONE_H-100 && lastIntYPos > e.getY() && scrolling == false) {
					homeScreen.appToDisplay(0);
				} else {
				scrollAmount = (e.getY() + lastIntYPos)/2;
				homeScreen.scrollApp(0, scrollAmount, lastIntYPos);
				lastIntYPos = e.getY();
				scrolling = true;
				}
				repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform tX = g2.getTransform();
		if (introScreen) {
		g2.setColor(Color.white);
		g2.fillRect(0, 0, PHONE_W, PHONE_H);
		g2.setColor(Color.black);
		g2.drawString("Hello there, and welcome to my phone app", PHONE_W/4, PHONE_H/2);
		g2.drawString("In this process, you will be using a stripped down iPhone", PHONE_W/5, PHONE_H/2 + 20);
		g2.drawString("Please click to continue", PHONE_W/3, PHONE_H/2 + 40);
		}
		g2.setTransform(tX);
		if (introScreen == false) {
			homeScreen.draw(g2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
