package jabberPoint.model;

import java.io.PrintWriter;
import java.util.Vector;

/**
 * Class responsible for showing a slide with the table of contents.
 */
public class TableOfContentsSlide extends Slide {
	/** A reference to the presentation **/
	private Presentation presentation;

	/**
	 * Creates a new table of contents slide.
	 * @param presentation - A reference to the presentation.
	 * @param title - The title of the slide.
	 */
	public TableOfContentsSlide(Presentation presentation, TextItem title) {
		super(title);
		this.presentation = presentation;
	}

	/**
	 * Re-generates the table of contents.
	 */
	@Override
	public Vector<SlideItem> getSlideItems() {
		Vector<SlideItem> items = new Vector<SlideItem>();
		
		// keep track of the last subject so we don't repeat it
		String lastSubject = "";
		// the current subject gets a special style (i.e. the subject after the table of contents)
		boolean isCurrent = false;

		// go through all the slides in the presentation
		int slideCount = this.presentation.getSize();
		for (int i = 0; i < slideCount; ++i) {
			Slide slide = this.presentation.getSlide(i);
			if (slide instanceof TableOfContentsSlide) {
				// the current subject is the one right after this table of contents.
				isCurrent = slide == this;
			}
			else if (slide instanceof ContentSlide) {
				String subject = getSlideSubject((ContentSlide) slide);
				if (!subject.equals(lastSubject)) {
					// the subject changed, let's add it to the slide items.
					int level = isCurrent ? 1 : 2; // give a higher level to the current subject
					items.add(new TextItem(level, "• " + subject));
					lastSubject = subject;
					isCurrent = false;
				}
			} else {
				System.err.printf("Unknown slide type %s", slide);
			}
		}
		return items;
	}

	/**
	 * Gets the subject of the given slide.
	 * @param slide: The slide.
	 * @return: The subject, or the title if the subject is empty.
	 */
	private String getSlideSubject(ContentSlide slide) {
		String subject = ((ContentSlide)slide).getSubject();
		if (subject == null || subject.isEmpty()) {
			// Use the title of the slide as default if no subject is given.
			subject = slide.getTitle();
		}
		return subject;
	}

	/**
	 * Writes the table of contents slide to the output.
	 * @param out: The printer writer.
	 */
	@Override
	public void writeXML(PrintWriter out) {
		out.println("<toc>");
		out.println("<title>" + getTitle() + "</title>");
		out.println("</toc>");
	}
}
