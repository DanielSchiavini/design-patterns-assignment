package jabberPoint.controller.factories;

import java.awt.MenuBar;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.function.BiConsumer;

import jabberPoint.controller.KeyController;
import jabberPoint.controller.MenuController;
import jabberPoint.model.Presentation;
import jabberPoint.model.factories.PresentationFactory;

/**
 * The controller factory is responsible for creating the controller classes.
 * @author Daniel Schiavini
 */
public class ControllerFactory {
	/** An instance to the presentation factory **/
	private final PresentationFactory presentationFactory;

	/**
	 * Creates a new controller factory.
	 * @param presentationFactory: An instance to the presentation factory.
	 */
	public ControllerFactory(PresentationFactory presentationFactory) {
		this.presentationFactory = presentationFactory;
	}
	
	/**
	 * Creates a key controller.
	 * @param presentation: The presentation to create a controller for.
	 * @return: The created controller.
	 */
	public KeyListener getKeyController(Presentation presentation) {
		return new KeyController(presentation);
	}

	/**
	 * Creates the menu controller.
	 * @param presentation: The presentation to create a controller for.
	 * @param onAboutRequested: Handler to show the about box.
	 * @param onIOException: Handler to show an IO exception.
	 * @return: The created controller.
	 */
	public MenuBar getMenuController(Presentation presentation, ActionListener onAboutRequested,
			BiConsumer<String, IOException> onIOException) {
		return new MenuController(presentation, onAboutRequested, onIOException, presentationFactory);
	}
}
