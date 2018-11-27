package jabberPoint.view.factories;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TextItem;
import jabberPoint.view.BitmapItemView;
import jabberPoint.view.SlideItemView;
import jabberPoint.view.TextItemView;

public class SlideItemViewFactory {
	public SlideItemView getItemView(SlideItem item) {
		if (item instanceof TextItem) {
			return new TextItemView((TextItem) item, Constants.WIDTH);
		}
		if (item instanceof BitmapItem) {
			return new BitmapItemView((BitmapItem) item);
		}
		return null;
	}
}
