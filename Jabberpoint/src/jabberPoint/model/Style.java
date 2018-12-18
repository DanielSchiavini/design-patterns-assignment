package jabberPoint.model;
import java.awt.Color;
import java.awt.Font;

/**
 * The style class, which is responsible for defining how the text should look like.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
*/
public class Style {
	/** The text color. **/
	private Color color;
	
	/** The text font **/
	private Font font;
	
	/** The font size in points. **/
	private int fontSize;
	
	/** The size of the left margin (i.e. the indentation level). **/
	private int leftMargin;
	
	/** The size of the top margin (i.e. the leading space). **/
	private int topMargin;

	/**
	 * Creates a new style instance.
	 * @param fontName: The name of the font.
	 * @param color: The text color.
	 * @param fontSize: The font size in points.
	 * @param leftMargin: The size of the left margin (i.e. the indentation level).
	 * @param topMargin: The size of the top margin (i.e. the leading space).
	 */
	public Style(String fontName, Color color, int fontSize, int leftMargin, int topMargin) {
		this.color = color;
		font = new Font(fontName, Font.BOLD, fontSize);
		this.fontSize = fontSize;
		this.leftMargin = leftMargin;
		this.topMargin = topMargin;
	}

	/**
	 * Gets the font for this style.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @return The font.
	 */
	public Font getFont(float scale) {
		return font.deriveFont(fontSize * scale);
	}

	/**
	 * Gets the left margin for this style.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @return The margin.
	 */
	public int getLeftMargin(float scale) {
		return (int) (this.leftMargin * scale);
	}

	/**
	 * Gets the top margin for this style.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @return The margin.
	 */
	public int getTopMargin(float scale) {
		return (int) (this.topMargin * scale);
	}

	/**
	 * Gets the color for this style.
	 * @return The color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Converts the style into a string.
	 */
	public String toString() {
		return "["+ leftMargin + "," + color + "; " + fontSize + " on " + topMargin +"]";
	}
}
