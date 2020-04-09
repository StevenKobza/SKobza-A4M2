/**
 * @author Steven Kobza
 * @version 1.0
 * <h1> Application Abstract Class </h1>
 * <p> This class isn't really used for much besides just passing a static
 * frame dimension between classes without needing to pass it as a variable
 * each and every time</p>
 */

package abstractClasses;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import storageClasses.ImageHolder;

public abstract class Application {
	protected static Dimension frameDim;
	protected static ArrayList<ImageHolder> images;
	protected static String wallpaperName;
	public Application(Dimension frameDim) {
		Application.frameDim = frameDim;
		images = new ArrayList<ImageHolder>();
	}
	public Application() {
		
	}
	public static void setImages(ArrayList<ImageHolder> i) {
		images = i;
	}
	protected abstract boolean clicked(MouseEvent e);
	protected abstract void draw(Graphics2D g2);
}
