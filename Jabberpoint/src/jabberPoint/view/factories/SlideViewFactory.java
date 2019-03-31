package jabberPoint.view.factories;

import jabberPoint.model.Slide;
import jabberPoint.view.SlideView;

/**
 * The slide view factory is responsible for creating the slide view. 
 * @author Daniel Schiavini
 */
public class SlideViewFactory {
	/** Object responsible for creating slide items. **/
	private SlideItemViewFactory itemViewFactory;
	
	/**
	 * Creates a new slide view factory.
	 * @param itemViewFactory: Object responsible for creating slide items.
	 */
	public SlideViewFactory(SlideItemViewFactory itemViewFactory) {
		this.itemViewFactory = itemViewFactory;
	}
	
	/**
	 * Creates the view for the given slide.
	 * @param slide: The slide.
	 * @return The slide view.
	 */
	public SlideView createSlideView(Slide slide) {
		return new SlideView(slide, itemViewFactory, Constants.PREFERRED_WIDTH, Constants.PREFERRED_HEIGHT);
	}
}
