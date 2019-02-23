package jabberPoint.model;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

/**
 * A slide item that is able to display text.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class TextItem extends SlideItem {
	/** The text in this slide item **/
	private String text;

	/**
	 * Creates a text slide item.
	 * @param level: The style level of the item (0 for the highest level).
	 * @param text: The text of the item.
	 */
	public TextItem(int level, String text) {
		super(level);
		this.text = text;
	}

	/**
	 * Gets the text of this text item.
	 * @return The text.
	 */
	public String getText() {
		return text == null ? "" : text;
	}

	/**
	 * Returns a boolean indicating whether the text of this item is null or empty.
	 * @return True if the text is empty, False otherwise.
	 */
	public boolean isEmpty() {
		return text == null || text.length() == 0;
	}

	/**
	 * Gets the attributed string for this item.
	 * @param style: The style to apply.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @return The attributed string.
	 */
	public AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(getText());
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
		return attrStr;
	}

	/**
	 * Converts the text item into a string.
	 */
	public String toString() {
		return "TextItem[" + getLevel()+","+getText()+"]";
	}

	@Override
	public String getStringContents() {
		return text;
	}

	@Override
	public String getSlideType() {
		return "text";
	}
}
