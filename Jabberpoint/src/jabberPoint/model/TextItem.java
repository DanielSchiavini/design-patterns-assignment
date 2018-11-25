package jabberPoint.model;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class TextItem extends SlideItem {
	private String text;
	
	private static final String EMPTYTEXT = "No Text Given";

	// een textitem van level level, met als tekst string
	public TextItem(int level, String string) {
		super(level);
		text = string;
	}

	// een leeg textitem
	public TextItem() {
		this(0, EMPTYTEXT);
	}

	// Geef de tekst
	public String getText() {
		return text == null ? "" : text;
	}

	// geef de AttributedString voor het item
	public AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(getText());
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
		return attrStr;
	}

	public String toString() {
		return "TextItem[" + getLevel()+","+getText()+"]";
	}

	public boolean isEmpty() {
		return text == null || text.length() == 0;
	}
}
