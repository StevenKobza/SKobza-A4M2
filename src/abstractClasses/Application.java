package abstractClasses;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class Application {
	protected static Dimension frameDim;
	public Application(Dimension frameDim) {
		Application.frameDim = frameDim;
	}
	public Application() {
		
	}
	protected abstract boolean clicked(MouseEvent e);
	protected abstract void draw(Graphics2D g2);
}
