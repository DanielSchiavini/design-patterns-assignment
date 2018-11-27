package jabberPoint.view.factories;

import jabberPoint.model.Presentation;
import jabberPoint.view.PresentationView;

public class PresentationViewFactory {
	public PresentationView getPresentationView(Presentation presentation) {
		SlideViewFactory factory = new SlideViewFactory();
		PresentationView view = new PresentationView(presentation, factory, Constants.WIDTH, Constants.HEIGHT);
		presentation.addObserver(view);
		return view;
	}
}
