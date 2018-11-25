package jabberPoint.model;
import java.util.Vector;

/** <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	/* Geen String meer maar een TextItem */
	private TextItem title; // de titel wordt apart bewaard
	private Vector<SlideItem> items; // de slide-items worden in een Vector bewaard
	private String subject = null;

	public Slide() {
		items = new Vector<SlideItem>();
	}

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

	// geef de titel van de slide
	public String getTitle() {
		/* Geef nu de tekst van het TextItem terug */
		return title.getText();
	}

	// geef de titel slide item van de slide
	public TextItem getTitleItem() {
		return title;
	}

	// verander de titel van de slide
	public void setTitle(String newTitle) {
		/* Creëer nu een TextItem op basis van de nieuwe titel */
		title = new TextItem(0, newTitle);
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

	// geef het betreffende SlideItem
	public SlideItem getSlideItem(int number) {
		return (SlideItem)items.elementAt(number);
	}

	// geef alle SlideItems in een Vector
	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	// geef de afmeting van de Slide
	public int getSize() {
		return items.size();
	}
	
	public void prepare() {
		// TODO: it's ugly to have this empty method
	}

}
