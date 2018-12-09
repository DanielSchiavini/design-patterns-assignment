package jabberPoint.view.factories;

import jabberPoint.model.Slide;
import jabberPoint.model.factories.StyleFactory;
import jabberPoint.view.SlideView;

/**
 * The slide view factory is responsible for creating the slide view. 
 * @author Daniel Schiavini
 */
public class SlideViewFactory {
	private SlideItemViewFactory itemViewFactory;
	private StyleFactory styleFactory;
	
	/**
	 * Creates a new slide view factory.
	 * @param itemViewFactory: The class responsible for creating slide items.
	 * @param styleFactory: The class responsible for creating styles.
	 */
	public SlideViewFactory(SlideItemViewFactory itemViewFactory, StyleFactory styleFactory) {
		this.itemViewFactory = itemViewFactory;
		this.styleFactory = styleFactory;
	}
	
	/**
	 * Gets the view for the given slide.
	 * @param slide: The slide.
	 * @return The slide view.
	 */
	public SlideView getSlideView(Slide slide) {
		return new SlideView(slide, itemViewFactory, styleFactory, Constants.PREFERRED_WIDTH, Constants.PREFERRED_HEIGHT);
	}
}
