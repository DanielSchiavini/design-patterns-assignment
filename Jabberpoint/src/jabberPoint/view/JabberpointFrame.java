package jabberPoint.view;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jabberPoint.controller.factories.ControllerFactory;
import jabberPoint.model.Presentation;


/**
 * This is the main frame for the Jabberpoint application.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class JabberpointFrame extends JFrame {
	private static final long serialVersionUID = 3227L;

	// string constants
	protected static final String IO_EXCEPTION = "IO Exception: %s";
	protected static final String ABOUT = "About";

	/**
	 * Creates a new Jabberpoint frame.
	 * @param presentation - The presentation to display.
	 * @param presentationView - The presentation view.
	 * @param controllerFactory - The controller factory.
	 */
	public JabberpointFrame(Presentation presentation, PresentationView presentationView,
			ControllerFactory controllerFactory) {
		super(presentation.getTitle());

		// setup window
		addWindowListener(windowAdapter);
		getContentPane().add(presentationView);
		setSize(presentationView.getPreferredSize());

		// setup controllers
		addKeyListener(controllerFactory.getKeyController(presentation));
		setMenuBar(controllerFactory.getMenuController(presentation, onAboutRequested, onIOException));
	}

	/**
	 * The adapter responsible for handling window events.
	 */
	private WindowAdapter windowAdapter = new WindowAdapter() {
		/**
		 * Exits cleanly when the application is being closed.
		 * @param e: The window event.
		 */
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};
	
	/**
	 * Shows a message dialog when the user requests the about box.
	 * @param e: The action event.
	 */
	private ActionListener onAboutRequested = e -> 
		JOptionPane.showMessageDialog(this,
				"JabberPoint is a primitive slide-show program in Java(tm). It\n" +
				"is freely copyable as long as you keep this notice and\n" +
				"the splash screen intact.\n" +
				"Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.\n" +
				"Adapted by Gert Florijn (version 1.1) and " +
				"Sylvia Stuurman (version 1.2 and higher) for the Open" +
				"University of the Netherlands, 2002 -- now." +
				"Author's version available from http://www.darwinsys.com/",
				"About JabberPoint",
				JOptionPane.INFORMATION_MESSAGE
		);

	/**
	 * Shows a message dialog when the user requests the about box.
	 * @param title: The title of the error.
	 * @param exc: The exception instance.
	 */
	private BiConsumer<String, IOException> onIOException = (title, exc) -> 
		JOptionPane.showMessageDialog(this, String.format(IO_EXCEPTION, exc.toString()),
				title, JOptionPane.ERROR_MESSAGE);
}
