/**
 * @author Steven Kobza
 * @version 1.0
 * <h1> Main Application </h1>
 * <p> this is the belly of the beast, or in other words, where you should
 * start the running from</p>
 */

package mainClasses;

import java.awt.Window;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PhoneMain extends JFrame{
	
	public PhoneMain(String name) {
		super(name);
		int frameRate = 60;
		if (System.getProperty("os.name") == "Windows 10") {
			this.setSize(516, 1000);
		} else {
			this.setSize(500, 1000);
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		PhonePanel phoneP = new PhonePanel(frameRate);
		this.add(phoneP);
		this.setFocusable(true);
		this.setVisible(true);
		phoneP.requestFocus();
	}

	public static void main(String[] args) {
		new PhoneMain("My Phone");
	}
}
