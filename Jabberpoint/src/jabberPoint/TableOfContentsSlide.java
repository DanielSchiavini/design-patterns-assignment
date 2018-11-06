package jabberPoint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class TableOfContentsSlide extends Slide {

	private static String DEFAULT_TITLE = "Inhoudsopgave";
	private Presentation presentation;

	public TableOfContentsSlide(Presentation presentation, String title) {
		super();
		setTitle(title);
		this.presentation = presentation;
	}

	public TableOfContentsSlide(Presentation presentation) {
		this(presentation, DEFAULT_TITLE);
	}

	public void setTitle(String newTitle) {
		if (newTitle == null || newTitle.isEmpty()) {
			newTitle = DEFAULT_TITLE;
		}
		super.setTitle(newTitle);
	}
	
	public String getSubject() {
		return null;
	}
	
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		items.clear();
		String lastSubject = "";
		int slideCount = this.presentation.getSize();
		boolean isCurrent = false;
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
				subject = slide.getTitle();
			}
			if (subject != lastSubject) {
				int level = isCurrent ? 1 : 2;
				append(level, subject);
				lastSubject = subject;
				isCurrent = false;
			}
		}
		super.draw(g, area, view);
	}
}
