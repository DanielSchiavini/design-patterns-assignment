package jabberPoint.view;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;

import jabberPoint.model.TextItem;

import java.util.Iterator;
import java.util.ArrayList;


/**
 * The text item view is responsible for displaying the text items.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class TextItemView implements SlideItemView {
	
	/** The text item being displayed **/
	private TextItem item;
	
	/** The base width of the presentation, excluding scaling **/
	private float preferredWidth;

	/** The style of the item. **/
	private Style style;

	/**
	 * Creates a new text item view.
	 * @param item: The text item.
	 * @param style: The style of the item.
	 * @param preferredWidth: The base width of the presentation, excluding scaling.
	 */
	public TextItemView(TextItem item, Style style, float preferredWidth) {
		this.item = item;
		this.style = style;
		this.preferredWidth = preferredWidth;
	}

	/**
	 * Gets the bounding box for the text slide item.
	 * @param g: The graphics instance.
	 * @param observer: The image observer.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @param style: The style of the slide item.
	 * @return The rectangle representing the bounding box.
	 */
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale) {
		List<TextLayout> layouts = getLayouts(g, style, scale);
		int xsize = 0, ysize = style.getTopMargin(scale);
		Iterator<TextLayout> iterator = layouts.iterator();
		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle(style.getLeftMargin(scale), 0, xsize, ysize );
	}

	/**
	 * Draws the text slide item.
	 * @param g: The graphics instance.
	 * @param view: The image observer.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @param x: The x-axis location where to write the item.
	 * @param y: The y-axis location where to write the item.
	 */
	public void draw(Graphics g, ImageObserver o, float scale, int x, int y) {
		if (item.isEmpty()) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, style, scale);
		Point pen = new Point(x + style.getLeftMargin(scale), y + style.getTopMargin(scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(style.getColor());
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	}

	/**
	 * Gets the layouts for each line of text to be displayed.
	 * @param g: The graphics instance.
	 * @param style: The style of the text item.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @return: The text layouts.
	 */
	private List<TextLayout> getLayouts(Graphics g, Style style, float scale) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(item.getText(), style.getFont(scale));
		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
		float wrappingWidth = (preferredWidth - style.getLeftMargin(1)) * scale;
		while (measurer.getPosition() < item.getText().length()) {
			TextLayout layout = measurer.nextLayout(wrappingWidth);
			layouts.add(layout);
		}
		return layouts;
	}

	/**
	 * Gets the attributed string for the given text and font.
	 * @param text: The text contents.
	 * @param font: The font to use.
	 * @return The attributed string.
	 */
	private AttributedString getAttributedString(String text, Font font) {
		AttributedString attrStr = new AttributedString(text);
		attrStr.addAttribute(TextAttribute.FONT, font, 0, text.length());
		return attrStr;
	}
}
