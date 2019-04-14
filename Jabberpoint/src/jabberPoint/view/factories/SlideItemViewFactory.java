package jabberPoint.view.factories;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.Slide;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TextItem;
import jabberPoint.view.BitmapItemView;
import jabberPoint.view.SlideItemView;
import jabberPoint.view.Style;
import jabberPoint.view.TextItemView;

/**
 * The slide item view factory is responsible for creating the slide item views. 
 * @author Daniel Schiavini
 */
public class SlideItemViewFactory {
	/** Object responsible for creating styles. **/
	private StyleFactory styleFactory;

	/**
	 * Creates a new slide item view factory.
	 * @param styleFactory: Object responsible for creating styles.
	 */
	public SlideItemViewFactory(StyleFactory styleFactory) {
		this.styleFactory = styleFactory;
	}

	/**
	 * Creates the slide item view.
	 * @param item: The slide item.
	 * @return The slide item view.
	 */
	public SlideItemView createItemView(Slide slide, SlideItem item) {
		Style style = styleFactory.createStyle(slide, item);
		if (item instanceof TextItem) {
			return new TextItemView((TextItem)item, style, Constants.PREFERRED_WIDTH);
		}
		if (item instanceof BitmapItem) {
			return new BitmapItemView((BitmapItem)item, style);
		}
		return null;
	}
}
