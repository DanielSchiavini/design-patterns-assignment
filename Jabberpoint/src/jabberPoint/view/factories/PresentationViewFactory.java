package jabberPoint.view.factories;

import javax.swing.JFrame;

import jabberPoint.model.Presentation;
import jabberPoint.view.PresentationView;

public class PresentationViewFactory {

	private static PresentationViewFactory instance = new PresentationViewFactory();

	private PresentationViewFactory() {
	}
	
	public static PresentationViewFactory getInstance() {
		return instance;
	}

	public PresentationView getPresentationView(Presentation presentation, JFrame frame) {
		PresentationView view = new PresentationView(presentation, frame, Constants.WIDTH, Constants.HEIGHT);
		presentation.addObserver(view);
		return view;
	}
}
