package jabberPoint;

import javax.swing.JOptionPane;

import jabberPoint.controller.factories.ControllerFactory;
import jabberPoint.model.Presentation;
import jabberPoint.model.PresentationReader;
import jabberPoint.model.factories.PresentationFactory;
import jabberPoint.model.factories.SlideFactory;
import jabberPoint.view.JabberpointFrame;
import jabberPoint.view.factories.PresentationViewFactory;
import jabberPoint.view.factories.SlideItemViewFactory;
import jabberPoint.view.factories.SlideViewFactory;
import jabberPoint.view.factories.StyleFactory;

import java.io.IOException;

/**
 * JabberPoint Main Program.
 * 
 * This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.
 * 
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class JabberPoint {
	// Text constants for errors
	private static final String IO_ERROR = "IO Error: %s";
	private static final String ERROR_TITLE = "Jabberpoint Error";

	// Dependencies setup
	private static final PresentationFactory presentationFactory = new PresentationFactory(new SlideFactory());
	private static final SlideViewFactory slideViewFactory = new SlideViewFactory(new SlideItemViewFactory(new StyleFactory()));
	private static final ControllerFactory controllerFactory = new ControllerFactory(presentationFactory);
	private static final PresentationViewFactory presentationViewFactory = new PresentationViewFactory(slideViewFactory, controllerFactory);

	/**
	 * The main method that starts up Jabberpoint.
	 * @param argv: Parameters given in the command line.
	 * 		If an argument was given, the file with the given name will be opened.
	 * 		If no argument was given, the demonstration presentation is displayed.
	 */
	public static void main(String argv[]) {
		try {
			String fileName = argv.length == 0 ? null : argv[0];
			PresentationReader reader = presentationFactory.createReader(fileName);
			Presentation presentation = reader.read();
			JabberpointFrame frame = presentationViewFactory.createFrame(presentation);
			presentation.setSlideNumber(0);
			frame.setVisible(true);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, String.format(IO_ERROR, ex), ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
		}
	}
}
