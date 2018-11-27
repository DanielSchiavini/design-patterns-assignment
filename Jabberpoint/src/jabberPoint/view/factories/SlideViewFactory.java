package jabberPoint.view.factories;

import jabberPoint.model.Slide;
import jabberPoint.view.SlideView;

public class SlideViewFactory {
	public SlideView getSlideView(Slide slide) {
		SlideItemViewFactory factory = new SlideItemViewFactory();
		return new SlideView(slide, factory, Constants.WIDTH, Constants.HEIGHT);
	}
}
