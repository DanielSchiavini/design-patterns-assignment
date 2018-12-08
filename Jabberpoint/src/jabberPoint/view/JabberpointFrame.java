package jabberPoint.view;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jabberPoint.controller.factories.ControllerFactory;
import jabberPoint.model.Presentation;


/**
 * This is the main frame for the Jabberpoint application.
 * @author TODO
 */
public class JabberpointFrame extends JFrame {
	private static final long serialVersionUID = 3227L;
	protected static final String IO_EXCEPTION = "IO Exception: %s";
	protected static final String ABOUT = "About";
	private PresentationView presentationView;
	
	/**
	 * Creates a new Jabberpoint frame.
	 * @param presentation - The presentation to display.
	 * @param presentationView - The presentation view.
	 * @param controllerFactory - 
	 */
	public JabberpointFrame(Presentation presentation, PresentationView presentationView,
			ControllerFactory controllerFactory) {
		super(presentation.getTitle());
		this.presentationView = presentationView;
		setupWindow();
		addKeyListener(controllerFactory.getKeyController(presentation));
		
		ActionListener onAboutRequested = e -> this.onAboutRequested(e);
		BiConsumer<String, IOException> onIOException = (title, exc) -> this.onIOException(title, exc);
		setMenuBar(controllerFactory.getMenuController(presentation, onAboutRequested, onIOException));
	}

	public void setupWindow() {
		addWindowListener(windowAdapter);
		getContentPane().add(presentationView);
		setSize(presentationView.getPreferredSize());
		setVisible(true);
	}

	private WindowAdapter windowAdapter = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	};
	
	private void onIOException(String title, IOException exc) {
		JOptionPane.showMessageDialog(this, String.format(IO_EXCEPTION, exc.toString()), title, JOptionPane.ERROR_MESSAGE);
	}

	public void onAboutRequested(ActionEvent actionEvent) {
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
	}
}
