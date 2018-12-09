package jabberPoint.model.factories;

import java.awt.Color;

import jabberPoint.model.Style;

/**
 * The style factory is responsible for creating the style class.
 * @author Daniel Schiavini.
 */
public class StyleFactory {
	/** The styles **/
	private Style[] styles;
	
	/**
	 * Creates a new style factory.
	 */
	public StyleFactory() {
		styles = new Style[5];
		styles[0] = new Style(Color.red, 48,   0, 20);	// style for item-level 0
		styles[1] = new Style(Color.blue, 40,  20, 10);	// style for item-level 1
		styles[2] = new Style(Color.black, 36, 50, 10);	// style for item-level 2
		styles[3] = new Style(Color.black, 30, 70, 10);	// style for item-level 3
		styles[4] = new Style(Color.black, 24, 90, 10);	// style for item-level 4
	}

	/**
	 * Gets the style for the given level.
	 * @param level: The style level (0 for the highest level).
	 * @return The style.
	 */
	public Style getStyle(int level) {
		if (level >= styles.length) {
			level = styles.length - 1;
		}
		return styles[level];
	}
}
