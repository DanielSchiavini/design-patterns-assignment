package jabberPoint.view;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

import jabberPoint.controller.KeyController;
import jabberPoint.controller.MenuController;
import jabberPoint.model.Presentation;
import jabberPoint.view.factories.PresentationViewFactory;


public class JabberpointFrame extends JFrame {
	private static final long serialVersionUID = 3227L;
	
	private static final String JABTITLE = "Jabberpoint 1.6 - OU";
	private static final PresentationViewFactory presentationViewFactory = PresentationViewFactory.getInstance();
	
	public JabberpointFrame(String title, Presentation presentation) {
		super(title);
		setupWindow(presentation);
	}

	// De GUI opzetten
	public void setupWindow(Presentation presentation) {
		setTitle(JABTITLE);
		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		PresentationView presentationView = presentationViewFactory.getPresentationView(presentation, this);
		getContentPane().add(presentationView);
		addKeyListener(new KeyController(presentation)); // een controller toevoegen
		setMenuBar(new MenuController(this, presentation));	// nog een controller toevoegen
		setSize(presentationView.getPreferredSize()); // Dezelfde maten als Slide hanteert.
		setVisible(true);
	}
}
