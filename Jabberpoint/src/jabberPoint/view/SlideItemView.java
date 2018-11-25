package jabberPoint.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import jabberPoint.model.Style;

public interface SlideItemView {
	// Geef de bounding box
	Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);
	
	// teken het item
	void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}
