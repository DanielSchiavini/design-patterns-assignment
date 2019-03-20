package jabberPoint.view.factories;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TextItem;
import jabberPoint.view.BitmapItemView;
import jabberPoint.view.SlideItemView;
import jabberPoint.view.TextItemView;

/**
 * The slide item view factory is responsible for creating the slide item views. 
 * @author Daniel Schiavini
 */
public class SlideItemViewFactory {
	/**
	 * Creates the slide item view.
	 * @param item: The slide item.
	 * @return The slide item view.
	 */
	public SlideItemView createItemView(SlideItem item) {
		if (item instanceof TextItem) {
			return new TextItemView((TextItem) item, Constants.PREFERRED_WIDTH);
		}
		if (item instanceof BitmapItem) {
			return new BitmapItemView((BitmapItem) item);
		}
		return null;
	}
}
