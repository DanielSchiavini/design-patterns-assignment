package jabberPoint.view.factories;

import jabberPoint.model.Slide;
import jabberPoint.view.SlideView;

public class SlideViewFactory {

	private static SlideViewFactory instance = new SlideViewFactory();

	private SlideViewFactory() {
	}
	
	public static SlideViewFactory getInstance() {
		return instance;
	}

	public SlideView getSlideView(Slide slide) {
		return new SlideView(slide, Constants.WIDTH, Constants.HEIGHT);
	}
}
