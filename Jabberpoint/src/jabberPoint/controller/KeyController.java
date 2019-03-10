package jabberPoint.controller;
import java.awt.event.KeyEvent;

import jabberPoint.model.Presentation;

import java.awt.event.KeyAdapter;

/**
 * This is the KeyController (KeyListener), responsible for handling keyboard shortcuts.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
*/
public class KeyController extends KeyAdapter {
	/** A reference to the presentation, so commands can be given **/
	private Presentation presentation;

	/**
	 * Initializes a new key controller.
	 * @param presentation: The presentation instance.
	 */
	public KeyController(Presentation presentation) {
		this.presentation = presentation;
	}

	/**
	 * Handles a key being pressed.
	 * @param keyEvent: The key press event.
	 */
	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				presentation.showNextSlide();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				presentation.showPreviousSlide();
				break;
			case 'q':
			case 'Q':
				System.exit(0);
				break; // Break should never be reached
		}
	}
}
