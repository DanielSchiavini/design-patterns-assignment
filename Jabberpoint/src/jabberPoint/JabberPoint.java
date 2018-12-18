package jabberPoint;

import javax.swing.JOptionPane;

import jabberPoint.controller.factories.ControllerFactory;
import jabberPoint.model.Presentation;
import jabberPoint.model.factories.PresentationFactory;
import jabberPoint.model.factories.StyleFactory;
import jabberPoint.view.JabberpointFrame;
import jabberPoint.view.PresentationView;
import jabberPoint.view.factories.PresentationViewFactory;
import jabberPoint.view.factories.SlideItemViewFactory;
import jabberPoint.view.factories.SlideViewFactory;

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
	private static final PresentationFactory presentationFactory = new PresentationFactory();
	private static final SlideItemViewFactory itemViewFactory = new SlideItemViewFactory();
	private static final StyleFactory styleFactory = new StyleFactory();
	private static final SlideViewFactory slideViewFactory = new SlideViewFactory(itemViewFactory, styleFactory);
	private static final PresentationViewFactory presentationViewFactory = new PresentationViewFactory(slideViewFactory);
	private static final ControllerFactory controllerFactory = new ControllerFactory(presentationFactory);

	/**
	 * The main method that starts up Jabberpoint.
	 * @param argv: Parameters given in the command line.
	 * 		If an argument was given, the file with the given name will be opened.
	 * 		If no argument was given, the demonstration presentation is displayed.
	 */
	public static void main(String argv[]) {
		try {
			Presentation presentation = argv.length == 0
				? presentationFactory.getDemoPresentation()
				: presentationFactory.readPresentation(argv[0]);
			PresentationView presentationView = presentationViewFactory.getPresentationView(presentation);
			new JabberpointFrame(presentation, presentationView, controllerFactory);
			presentation.setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, String.format(IO_ERROR, ex), ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
		}
	}
}
