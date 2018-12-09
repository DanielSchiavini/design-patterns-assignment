package jabberPoint.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import jabberPoint.model.Style;

/**
 * The slide item view is an interface for classes that display the slide items. 
 * @author Daniel Schiavini
 */
public interface SlideItemView {
	/**
	 * Gets the bounding box for the slide item.
	 * @param g: The graphics instance.
	 * @param view: The image observer.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @param style: The style of the slide item.
	 * @return The rectangle representing the bounding box.
	 */
	Rectangle getBoundingBox(Graphics g, ImageObserver view, float scale, Style style);
	
	/**
	 * Draws the slide item.
	 * @param g: The graphics instance.
	 * @param view: The image observer.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @param style: The style of the slide item.
	 * @param x: The x-axis location where to write the item.
	 * @param y: The y-axis location where to write the item.
	 */
	void draw(Graphics g, ImageObserver view, float scale, Style style, int x, int y);
}
