package jabberPoint.model;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * Class responsible for displaying a regular content slide.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class ContentSlide extends Slide {

	/**
	 * Creates a content slide.
	 * @param title: The slide title item.
	 */
	public ContentSlide(TextItem title) {
		super(title);
	}

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

	/**
	 * Writes a content slide to the output.
	 * @param out: The printer writer.
	 */
	public void writeXML(PrintWriter out) {
		out.println("<slide>");
		out.println("<title>" + getTitle() + "</title>");
		
		if (subject != null && !subject.isEmpty()) {
			out.println("<subject>" + subject + "</subject>");
		}

		for (int itemNumber = 0; itemNumber<items.size(); itemNumber++) {
			SlideItem slideItem = items.elementAt(itemNumber);
			slideItem.writeXML(out);
		}
		out.println("</slide>");
	}
}
