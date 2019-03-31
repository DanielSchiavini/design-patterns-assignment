package jabberPoint.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import jabberPoint.model.Slide;
import jabberPoint.model.SlideItem;
import jabberPoint.view.factories.SlideItemViewFactory;

/**
 * The slide view is responsible for displaying the slides.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class SlideView {
	/** The slide to display. **/
	private Slide slide;
	
	/** The class responsible for creating item views. **/
	private SlideItemViewFactory itemFactory;

	/** The base width of the slide, excluding scaling **/
	private int preferredWidth;

	/** The base height of the slide, excluding scaling **/
	private int preferredHeight;

	/**
	 * Creates a new slide view.
	 * @param slide: The slide to display.
	 * @param itemFactory: The class responsible for creating item views.
	 * @param preferredWidth: The base width of the slide, excluding scaling.
	 * @param preferredHeight: The base height of the slide, excluding scaling.
	 */
	public SlideView(Slide slide, SlideItemViewFactory itemFactory,
			int preferredWidth, int preferredHeight) {
		this.slide = slide;
		this.itemFactory = itemFactory;
		this.preferredWidth = preferredWidth;
		this.preferredHeight = preferredHeight;
	}

	/**
	 * Draws the slide.
	 * @param g: The graphics instance.
	 * @param area: The area where the slide can be painted.
	 * @param view: The image observer.
	 */
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
		int y = area.y;
		SlideItem titleItem = slide.getTitleItem();
		SlideItemView itemView = itemFactory.createItemView(slide, titleItem);
		itemView.draw(g, view, scale, area.x, y);
		y += itemView.getBoundingBox(g, view, scale).height;
		for (SlideItem item : slide.getSlideItems()) {
			itemView = itemFactory.createItemView(slide, item);
		  	itemView.draw(g, view, scale, area.x, y);
			y += itemView.getBoundingBox(g, view, scale).height;
		}
	}

	/**
	 * Gets the scale to define how big the items should be.
	 * This is based on the difference between the standard window size and the current size.
	 * @param area: The area where the slide can be painted.
	 */
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)preferredWidth), ((float)area.height) / ((float)preferredHeight));
	}
}
