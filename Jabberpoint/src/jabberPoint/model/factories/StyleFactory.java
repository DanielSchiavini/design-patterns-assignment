package jabberPoint.model.factories;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import jabberPoint.model.ContentSlide;
import jabberPoint.model.Slide;
import jabberPoint.model.TableOfContentsSlide;
import jabberPoint.model.Style;

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
	 * @param level: The style level (0 for the highest level).
	 * @return The style.
	 */
	public Style getStyle(Slide slide, int level) {
		Style[] classStyles = this.styles.get(slide.getClass());
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
		styles[0] = new Style(FONT_NAME, Color.red, 48,   0, 20);	// style for item-level 0
		styles[1] = new Style(FONT_NAME, Color.blue, 40,  20, 10);	// style for item-level 1
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
		styles[0] = new Style(FONT_NAME, Color.red, 48,   0, 20);	// style for item-level 0
		styles[1] = new Style(FONT_NAME, Color.blue, 36, 50, 10);
		styles[2] = new Style(FONT_NAME, Color.black, 36, 50, 10);
		return styles;
	}
}
