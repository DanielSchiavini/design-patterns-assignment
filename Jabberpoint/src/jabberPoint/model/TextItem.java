package jabberPoint.model;

import java.io.PrintWriter;

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
	 * Converts the text item into a string.
	 */
	public String toString() {
		return "TextItem[" + getLevel()+","+getText()+"]";
	}

	/**
	 * Writes the slide item to the output.
	 * @param out - The print writer.
	 */
	@Override
	public void writeXML(PrintWriter out)
	{
		out.print("<item kind=\"text\" level=\"" + getLevel() + "\">");
		out.print(text);
		out.println("</item>");
	}
}
