package jabberPoint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * Class responsible for showing a slide with the table of contents.
 */
public class TableOfContentsSlide extends Slide {

	// A reference to the presentation
	private Presentation presentation;

	/**
	 * Creates a new table of contents slide.
	 * @param presentation - A reference to the presentation.
	 * @param title - The title of the slide.
	 */
	public TableOfContentsSlide(Presentation presentation, String title) {
		this(presentation);
		setTitle(title);
	}

	/**
	 * Creates a new table of contents slide without a title.
	 * @param presentation - A reference to the presentation.
	 */
	public TableOfContentsSlide(Presentation presentation) {
		super();
		this.presentation = presentation;
	}

	/**
	 * Gets the subject of this slide. Note that tables of contents may not have a subject.
	 */
	public String getSubject() {
		return null;
	}

	/**
	 * Re-generates the table of contents.
	 */
	public void generateItems() {
		// delete all slide items
		clear();
		// keep track of the last subject so we don't repeat it
		String lastSubject = "";
		// the current subject gets a special style (i.e. the subject after the table of contents)
		boolean isCurrent = false;

		// go through all the slides in the presentation
		int slideCount = this.presentation.getSize();
		for (int i = 0; i < slideCount; ++i) {
			Slide slide = this.presentation.getSlide(i);
			if (slide instanceof TableOfContentsSlide) {
				// the current section is the one right after this table of contents.
				isCurrent = slide == this;
				// ignore any table of contents slide except the current one.
				continue;
			}
			
			String subject = slide.getSubject();
			if (subject == null || subject.isEmpty()) {
				// Use the title of the slide as default if no subject is given.
				subject = slide.getTitle();
			}

			if (!subject.equals(lastSubject)) {
				// the subject changed, let's add it to the slide items.
				int level = isCurrent ? 1 : 2;
				append(level, subject);
				lastSubject = subject;
				isCurrent = false;
			}
		}
	}

	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		// The table of contents must be automatically re-generated whenever slides change.
		// Since we do not have a mechanism to know when that happens, we re-generate in every draw.
		this.generateItems();
		super.draw(g, area, view);
	}
}
