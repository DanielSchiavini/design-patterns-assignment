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

public class ControllerFactory {
	private static final PresentationFactory presentationFactory = new PresentationFactory();
	
	public KeyListener getKeyController(Presentation presentation) {
		return new KeyController(presentation);
	}

	public MenuBar getMenuController(Presentation presentation, ActionListener onAboutRequested,
			BiConsumer<String, IOException> onIOException) {
		return new MenuController(presentation, onAboutRequested, onIOException, presentationFactory);
	}
}
