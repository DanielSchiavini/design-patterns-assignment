package jabberPoint.view.factories;

import jabberPoint.controller.factories.ControllerFactory;
import jabberPoint.model.Presentation;
import jabberPoint.view.JabberpointFrame;
import jabberPoint.view.PresentationView;

/**
 * The presentation view factory is responsible for creating the presentation view. 
 * @author Daniel Schiavini
 */
public class PresentationViewFactory {
	/** The class responsible for creating slide views **/
	private SlideViewFactory slideViewFactory;
	
	/**
	 * Creates a new presentation view factory.
	 * @param styleFactory: The class responsible for creating slide views.
	 */
	public PresentationViewFactory(SlideViewFactory styleFactory) {
		this.slideViewFactory = styleFactory;
	}

	/**
	 * Creates a new Jabberpoint frame.
	 * @param presentation: The presentation.
	 * @param controllerFactory: The controller factory.
	 * @return: The created frame.
	 */
	public JabberpointFrame createFrame(Presentation presentation, ControllerFactory controllerFactory) {
		PresentationView presentationView = this.createPresentationView(presentation);
		return new JabberpointFrame(presentation, presentationView, controllerFactory);
	}

	/**
	 * Creates a view for the given presentation.
	 * @param presentation: The presentation.
	 * @return The presentation view.
	 */
	private PresentationView createPresentationView(Presentation presentation) {
		PresentationView view = new PresentationView(presentation, slideViewFactory,
				Constants.PREFERRED_WIDTH, Constants.PREFERRED_HEIGHT);
		presentation.addObserver(view);
		return view;
	}
}
