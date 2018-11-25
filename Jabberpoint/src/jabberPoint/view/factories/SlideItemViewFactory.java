package jabberPoint.view.factories;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TextItem;
import jabberPoint.view.BitmapItemView;
import jabberPoint.view.SlideItemView;
import jabberPoint.view.TextItemView;

public class SlideItemViewFactory {
	private static SlideItemViewFactory instance = new SlideItemViewFactory();

	private SlideItemViewFactory() {
	}
	
	public static SlideItemViewFactory getInstance() {
		return instance;
	}

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
