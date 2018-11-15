package jabberPoint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * Class responsible for showing a slide with the table of contents.
 */
public class TableOfContentsSlide extends Slide {

	// title to be used if no title was given.
	private static String DEFAULT_TITLE = "Inhoudsopgave";
	
	// A reference to the presentation
	private Presentation presentation;

	/**
	 * Creates a new table of contents slide.
	 * @param presentation - A reference to the presentation.
	 * @param title - The title of the slide.
	 */
	public TableOfContentsSlide(Presentation presentation, String title) {
		super();
		setTitle(title);
		this.presentation = presentation;
	}

	/**
	 * Creates a new table of contents slide without a title.
	 * @param presentation - A reference to the presentation.
	 */
	public TableOfContentsSlide(Presentation presentation) {
		this(presentation, DEFAULT_TITLE);
	}

	/**
	 * Sets the title of the slide.
	 */
	public void setTitle(String newTitle) {
		if (newTitle == null || newTitle.isEmpty()) {
			newTitle = DEFAULT_TITLE;
		}
		super.setTitle(newTitle);
	}
	
	/**
	 * Re-generates the table of contents.
	 */
	public ContentSlide generate() {
		// keep track of the last subject so we don't repeat it
		String lastSubject = "";
		// the current subject gets a special style (i.e. the subject after the table of contents)
		boolean isCurrent = false;

		ContentSlide content = new ContentSlide();
		content.setTitle(getTitle());
		
		// go through all the slides in the presentation
		int slideCount = this.presentation.getSize();
		for (int i = 0; i < slideCount; ++i) {
			Slide slide = this.presentation.getSlide(i);
			if (slide instanceof TableOfContentsSlide) {
				// the current section is the one right after this table of contents.
				isCurrent = slide == this;
			} else if (slide instanceof ContentSlide) {
				ContentSlide customSlide = (ContentSlide)slide;
				String subject = customSlide.getSubject();
				if (subject == null || subject.isEmpty()) {
					// Use the title of the slide as default if no subject is given.
					subject = customSlide.getTitle();
				}
	
			if (!subject.equals(lastSubject)) {
					// the subject changed, let's add it to the slide items.
					int level = isCurrent ? 1 : 2;
					content.append(level, subject);
					lastSubject = subject;
					isCurrent = false;
				}
			}
		}
		return content;
	}

	/**
	 * Draws the table of contents
	 */
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		generate().draw(g, area, view);
	}
}
