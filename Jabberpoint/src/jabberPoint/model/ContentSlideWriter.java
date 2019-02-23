package jabberPoint.model;

import java.io.PrintWriter;
import java.util.Vector;

/**
 * Class responsible for writing content slides.
 * @author Daniel Schiavini
 */
public class ContentSlideWriter implements SlideWriter {
	// The slide being written
	private ContentSlide slide;

	/**
	 * Creates a new content slide writer.
	 * @param slide: The slide to write.
	 */
	public ContentSlideWriter(ContentSlide slide) {
		this.slide = slide;
	}

	/**
	 * Writes a content slide to the output.
	 * @param out: The printer writer.
	 */
	public void write(PrintWriter out) {
		out.println("<slide>");
		out.println("<title>" + slide.getTitle() + "</title>");
		
		String subject = slide.getSubject();
		if (subject != null && !subject.isEmpty()) {
			out.println("<subject>" + subject + "</subject>");
		}
		
		Vector<SlideItem> slideItems = slide.getSlideItems();
		for (int itemNumber = 0; itemNumber<slideItems.size(); itemNumber++) {
			SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
			writeSlideItem(slideItem, out);
		}
		out.println("</slide>");
	}

	/**
	 * Writes the slide item to the output.
	 * Note: Only text and bitmap slide items that are written; any other types are ignored.
	 * @param slideItem - The slide item to be written.
	 * @param out - The print writer.
	 */
	private void writeSlideItem(SlideItem slideItem, PrintWriter out) {
		out.print("<item "); 
		out.print("kind=\"" + slideItem.getSlideType() + "\" ");
		out.print("level=\"" + slideItem.getLevel());
		out.print("\">");
		out.print(slideItem.getStringContents());
		out.println("</item>");
	}
}
