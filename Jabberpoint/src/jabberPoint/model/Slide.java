package jabberPoint.model;
import java.util.Vector;

/**
 * The abstract class from which the different slide types should inherit.
 * @author Daniel Schiavini
 */
public abstract class Slide {
	/** The title of the slide **/
	private TextItem title;

	/**
	 * Gets the title of the slide.
	 * @return The title.
	 */
	public String getTitle() {
		return title.getText();
	}

	/**
	 * Gets the text item for the title of the slide.
	 * @return The title text item.
	 */
	public TextItem getTitleItem() {
		return title;
	}

	/**
	 * Sets the title of the slide.
	 * @param newTitle: The new title.
	 */
	public void setTitle(String newTitle) {
		title = new TextItem(0, newTitle);
	}

	/**
	 * Abstract method that should return all the slide items.
	 * @return The items in the slide.
	 */
	public abstract Vector<SlideItem> getSlideItems();
}
