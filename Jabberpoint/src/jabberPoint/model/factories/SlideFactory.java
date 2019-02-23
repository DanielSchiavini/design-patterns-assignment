package jabberPoint.model.factories;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.ContentSlide;
import jabberPoint.model.ContentSlideWriter;
import jabberPoint.model.Presentation;
import jabberPoint.model.Slide;
import jabberPoint.model.SlideItem;
import jabberPoint.model.SlideWriter;
import jabberPoint.model.TableOfContentsSlide;
import jabberPoint.model.TableOfContentsSlideWriter;
import jabberPoint.model.TextItem;

/**
 * The class responsible for creating slides.
 * @author Daniel Schiavini
 */
public class SlideFactory {

	// string constants
	protected static final String TEXT = "text";
	protected static final String IMAGE = "image";
	protected static final String UNKNOWN_TYPE = "Unknown Slide Item type %s!\n";

	/**
	 * Creates a table of contents slide.
	 * @param presentation: The presentation.
	 * @param title: The title of the slide.
	 * @return The table of contents slide.
	 */
	public TableOfContentsSlide createTableOfContents(Presentation presentation, String title) {
		return new TableOfContentsSlide(presentation, createTitleItem(title));
	}

	/**
	 * Creates a content slide.
	 * @param title: The title of the slide.
	 * @param subject: The subject of the slide.
	 * @return The table of contents slide.
	 */
	public ContentSlide createContentSlide(String title, String subject) {
		ContentSlide slide = new ContentSlide(createTitleItem(title));
		if (subject != null) {
			slide.setSubject(subject);
		}
		return slide;
	}

	/**
	 * Creates the text item for the title of a slide.
	 * @param title: The title of the slide.
	 * @return: The title item.
	 */
	private TextItem createTitleItem(String title) {
		return new TextItem(0, title);
	}

	/**
	 * Creates a slide item.
	 * @param type: The type of the slide.
	 * @param level: The level of the slide item.
	 * @param textContent: The text contents.
	 * @return: The created slide item.
	 */
	public SlideItem createItem(String type, int level, String textContent) {
		if (TEXT.equals(type)) {
			return new TextItem(level, textContent);
		}
		
		if (IMAGE.equals(type)) {
			return new BitmapItem(level, textContent);
		}
		
		System.err.printf(UNKNOWN_TYPE, type);
		return null;
	}

	/**
	 * Creates a slide writer
	 * @param slide: The slide that will be written.
	 * @return: The slide writer object.
	 */
	public SlideWriter createWriter(Slide slide) {
		if (slide instanceof TableOfContentsSlide) {
			return new TableOfContentsSlideWriter((TableOfContentsSlide) slide);
		}
		
		if (slide instanceof ContentSlide) {
			return new ContentSlideWriter((ContentSlide)slide);
		}
		
		if (slide != null) {
			System.err.printf(UNKNOWN_TYPE, slide.getClass().getName());
		}
		
		return null;
	}
}
