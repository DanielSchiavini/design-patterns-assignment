package jabberPoint.view.factories;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import jabberPoint.model.ContentSlide;
import jabberPoint.model.Slide;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TableOfContentsSlide;
import jabberPoint.view.Style;

/**
 * The style factory is responsible for creating the style class.
 * @author Daniel Schiavini.
 */
public class StyleFactory {
	/** The styles **/
	private Map<Class<? extends Slide>, Style[]> styles;
	private static final String FONT_NAME = "Helvetica";
	
	/**
	 * Creates a new style factory.
	 */
	public StyleFactory() {
		styles = new HashMap<Class<? extends Slide>, Style[]>();
		styles.put(TableOfContentsSlide.class, this.createTableOfContentStyles());
		styles.put(ContentSlide.class, this.createContentStyles());
	}

	/**
	 * Gets the style for the given level.
	 * @param slide: The slide.
	 * @param slideItem: The slide item.
	 * @return The style.
	 */
	public Style createStyle(Slide slide, SlideItem slideItem) {
		Style[] classStyles = this.styles.get(slide.getClass());
		int level = slideItem.getLevel();
		if (level >= classStyles.length) {
			level = classStyles.length - 1;
		}
		return classStyles[level];
	}

	/**
	 * Creates an array with the styles for the content slides.
	 * @return: An array of styles.
	 */
	private Style[] createContentStyles() {
		Style[] styles = new Style[5];
		styles[0] = new Style(FONT_NAME, Color.red,   48,  0, 20);	// style for item-level 0
		styles[1] = new Style(FONT_NAME, Color.blue,  40, 20, 10);	// style for item-level 1
		styles[2] = new Style(FONT_NAME, Color.black, 36, 50, 10);	// style for item-level 2
		styles[3] = new Style(FONT_NAME, Color.black, 30, 70, 10);	// style for item-level 3
		styles[4] = new Style(FONT_NAME, Color.black, 24, 90, 10);	// style for item-level 4
		return styles;
	}

	/**
	 * Creates an array with the styles for the table of content slides.
	 * @return: An array of styles.
	 */
	private Style[] createTableOfContentStyles() {
		Style[] styles = new Style[3];
		styles[0] = new Style(FONT_NAME, Color.red,   48,  0, 20);	// style for item-level 0
		styles[1] = new Style(FONT_NAME, Color.blue,  36, 50, 10);	// style for item-level 1
		styles[2] = new Style(FONT_NAME, Color.black, 36, 50, 10);	// style for item-level 2
		return styles;
	}
}
