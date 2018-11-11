package jabberPoint;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public abstract class Slide {
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;

	private String title; // de titel wordt apart bewaard

	// geef de titel van de slide
	public String getTitle() {
		/* Geef nu de tekst van het TextItem terug */
		return title;
	}

	// verander de titel van de slide
	public void setTitle(String newTitle) {
		/* Creëer nu een TextItem op basis van de nieuwe titel */
		title = newTitle;
	}
	
	public abstract void draw(Graphics g, Rectangle area, ImageObserver view);
}
