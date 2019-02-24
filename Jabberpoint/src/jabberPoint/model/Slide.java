package jabberPoint.model;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * The abstract class from which the different slide types should inherit.
 * @author Daniel Schiavini
 */
public abstract class Slide {
	/** The title of the slide **/
	private final TextItem title;
	
	/**
	 * Creates a slide.
	 * @param title: The slide title item.
	 */
	public Slide(TextItem title) {
		this.title = title;
	}

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
	 * Abstract method that should return all the slide items.
	 * @return The items in the slide.
	 */
	public abstract Vector<SlideItem> getSlideItems();

	/**
	 * Writes the slide to the output as XML.
	 * @param out: The printer writer.
	 */
	public abstract void writeXML(PrintWriter out);
}
