package jabberPoint.view;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

import jabberPoint.controller.factories.ControllerFactory;
import jabberPoint.model.Presentation;


public class JabberpointFrame extends JFrame {
	private static final long serialVersionUID = 3227L;
	private PresentationView presentationView;
	
	public JabberpointFrame(Presentation presentation, PresentationView presentationView,
			ControllerFactory controllerFactory) {
		super(presentation.getTitle());
		this.presentationView = presentationView;
		setupWindow(presentation);
		addKeyListener(controllerFactory.getKeyController(presentation));
		setMenuBar(controllerFactory.getMenuController(this, presentation));
	}

	public void setupWindow(Presentation presentation) {
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
}
