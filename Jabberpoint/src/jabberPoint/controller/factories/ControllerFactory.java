package jabberPoint.controller.factories;

import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.event.KeyListener;

import jabberPoint.controller.KeyController;
import jabberPoint.controller.MenuController;
import jabberPoint.model.Presentation;
import jabberPoint.model.factories.PresentationFactory;

public class ControllerFactory {

	public KeyListener getKeyController(Presentation presentation) {
		return new KeyController(presentation);
	}

	public MenuBar getMenuController(Frame parent, Presentation presentation) {
		PresentationFactory factory = new PresentationFactory();
		return new MenuController(parent, presentation, factory);
	}
}
