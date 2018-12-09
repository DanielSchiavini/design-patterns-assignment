package jabberPoint.model;
import java.util.Vector;

/**
 * Class responsible for displaying a regular content slide.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class ContentSlide extends Slide {

	/** The items in the slide **/
	private Vector<SlideItem> items = new Vector<SlideItem>();

	/** The subject of the slide (which is displayed in the table of contents slides) **/ 
	private String subject = null;

	/**
	 * Gets all the items in this slide.
	 */
	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	/**
	 * Appends an slide item to the slide.
	 * @param item: The item to be added.
	 */
	public void append(SlideItem item) {
		items.addElement(item);
	}

	/**
	 * Creates a new text item based on the given string and adds it to the slide.
	 * @param level: The style level of the item (0 for the highest level).
	 * @param text: The text of the item.
	 */
	public void append(int level, String text) {
		append(new TextItem(level, text));
	}

	/**
	 * Gets the subject of the slide, to be displayed in the table of contents slides.
	 * @return The slide subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the slide.
	 * @param subject: The new slide subject.
	 */
	public void setSubject(String subject) {
		this.subject  = subject;
	}
}
