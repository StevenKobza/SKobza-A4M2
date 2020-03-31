package mainClasses;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PhoneMain extends JFrame{
	
	public PhoneMain(String name) {
		super(name);
		int frameRate = 60;
		this.setSize(500, 1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		PhonePanel phoneP = new PhonePanel(frameRate);
		this.add(phoneP);
		this.setFocusable(true);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new PhoneMain("My Phone");
	}
}
