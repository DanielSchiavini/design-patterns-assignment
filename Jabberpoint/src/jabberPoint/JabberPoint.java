package jabberPoint;

import javax.swing.JOptionPane;

import jabberPoint.controller.factories.ControllerFactory;
import jabberPoint.model.Presentation;
import jabberPoint.model.Style;
import jabberPoint.model.factories.PresentationFactory;
import jabberPoint.view.JabberpointFrame;
import jabberPoint.view.PresentationView;
import jabberPoint.view.factories.PresentationViewFactory;

import java.io.IOException;

/** JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */

public class JabberPoint {
	private static final String IOERR = "IO Error: ";
	private static final String JABERR = "Jabberpoint Error ";

	private static final PresentationFactory presentationFactory = new PresentationFactory();
	private static final PresentationViewFactory presentationViewFactory = new PresentationViewFactory();
	private static final ControllerFactory controllerFactory = new ControllerFactory();

	/** Het Main Programma */
	public static void main(String argv[]) {
		
		Style.createStyles();
		try {
			Presentation presentation = argv.length == 0
				? presentationFactory.getDemoPresentation()
				: presentationFactory.readPresentation(argv[0]);
			PresentationView presentationView = presentationViewFactory.getPresentationView(presentation);
			new JabberpointFrame(presentation, presentationView, controllerFactory);
			presentation.setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
		}
	}
}
