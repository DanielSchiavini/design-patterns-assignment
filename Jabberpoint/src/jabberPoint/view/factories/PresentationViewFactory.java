package jabberPoint.view.factories;

import jabberPoint.model.Presentation;
import jabberPoint.view.PresentationView;

public class PresentationViewFactory {

	private static PresentationViewFactory instance = new PresentationViewFactory();

	private PresentationViewFactory() {
	}
	
	public static PresentationViewFactory getInstance() {
		return instance;
	}

	public PresentationView getPresentationView(Presentation presentation) {
		PresentationView view = new PresentationView(presentation, Constants.WIDTH, Constants.HEIGHT);
		presentation.addObserver(view);
		return view;
	}
}
