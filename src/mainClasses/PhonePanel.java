package mainClasses;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
import apps.*;

@SuppressWarnings("serial")
public class PhonePanel extends JPanel {
	private static final int PHONE_W = 500;
	private static final int PHONE_H = 1000;
	private double lastIntYPos;
	
	private int appSelected = 0; //0 = Home Screen, 1 = Calc, 2 = Canv, 3 = Photos, 4 = Settings
	HomeScreen homeScreen;
	
	public PhonePanel(int fr) {
		super();
		this.setPreferredSize(new Dimension(PHONE_W+50, PHONE_H));
		homeScreen = new HomeScreen(new Dimension(PHONE_W, PHONE_H));
		appSelected = 4;
		homeScreen.appToDisplay(0);
		
		lastIntYPos = 0;
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				lastIntYPos = 0;
			}
			public void mousePressed(MouseEvent e) {
				lastIntYPos = e.getY();
			}
			public void mouseClicked(MouseEvent e) {
				homeScreen.appClicked(e);
				repaint();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			double scrollAmount;
			public void mouseDragged(MouseEvent e) {
				//scrollAmount = e.getY() - lastIntYPos;
				if (e.getY() > PHONE_H-100 && lastIntYPos > e.getY()) {
					homeScreen.appToDisplay(0);
				} else {
				scrollAmount = (e.getY() + lastIntYPos)/2;
				homeScreen.scrollApp(0, scrollAmount, lastIntYPos);
				lastIntYPos = e.getY();
				}
				repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		homeScreen.draw(g2);
	}
}
