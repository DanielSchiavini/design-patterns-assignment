package jabberPoint.model;
import java.util.Vector;

public class ContentSlide extends Slide {

	private Vector<SlideItem> items = new Vector<SlideItem>();
	private String subject = null;

	// Voeg een SlideItem toe
	public void append(SlideItem anItem) {
		items.addElement(anItem);
	}

	// Maak een TextItem van String, en voeg het TextItem toe
	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	// Verwijder alle slide items
	protected void clear() {
		items.clear();
	}

	// geef het onderwerp van de slide
	public String getSubject() {
		return subject;
	}

	// verander het onderwerp van de slide
	public void setSubject(String subject) {
		/* Creëer nu een TextItem op basis van de nieuwe titel */
		this.subject  = subject;
	}

	// geef alle SlideItems in een Vector
	public Vector<SlideItem> getSlideItems() {
		return items;
	}
}
