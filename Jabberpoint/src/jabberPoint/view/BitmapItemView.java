package jabberPoint.view;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

import jabberPoint.model.BitmapItem;


/**
 * The bitmap item view is responsible for displaying bitmap items. 
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class BitmapItemView implements SlideItemView {

	/** The image being displayed **/
	private BufferedImage bufferedImage;

	/** The style of the item. **/
	private Style style;
	
	/**
	 * Creates a new bitmap item view instance.
	 * @param item: The bitmap item.
	 * @param style: The style of the item.
	 */
	public BitmapItemView(BitmapItem item, Style style) {
		try {
			bufferedImage = ImageIO.read(item.getFile());
			this.style = style;
		} catch (IOException e) {
			System.err.printf("Cannot find image file %s\n", item.getName()) ;
		}
	}

	/**
	 * Gets the bounding box for the bitmap slide item.
	 * @param g: The graphics instance.
	 * @param observer: The image observer.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @return The rectangle representing the bounding box.
	 */
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale) {
		return new Rectangle((int) (style.getLeftMargin(scale)), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (style.getTopMargin(scale))) + 
				(int) (bufferedImage.getHeight(observer) * scale));
	}

	/**
	 * Draws the bitmap slide item.
	 * @param g: The graphics instance.
	 * @param view: The image observer.
	 * @param scale: The scale to apply (depending on the amount of space available).
	 * @param x: The x-axis location where to write the item.
	 * @param y: The y-axis location where to write the item.
	 */
	public void draw(Graphics g, ImageObserver view, float scale, int x, int y) {
		int width = x + (int) (style.getLeftMargin(scale));
		int height = y + (int) (style.getTopMargin(scale));
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(view)*scale),
				(int) (bufferedImage.getHeight(view)*scale), view);
	}
}
