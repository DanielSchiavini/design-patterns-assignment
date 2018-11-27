package jabberPoint.controller;
import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import jabberPoint.model.Presentation;
import jabberPoint.model.factories.PresentationFactory;
import jabberPoint.view.AboutBox;

/** <p>De controller voor het menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	
	private Frame parent; // het frame, alleen gebruikt als ouder voor de Dialogs
	private Presentation presentation; // Er worden commando's gegeven aan de presentatie
	PresentationFactory presentationFactory;

	private static final long serialVersionUID = 227L;
	
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGE_NR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String TEST_FILE = "test.xml";
	protected static final String SAVE_FILE = "dump.xml";
	
	protected static final String IO_EXCEPTION = "IO Exception: ";
	protected static final String LOAD_ERROR = "Load Error";
	protected static final String SAVE_ERROR = "Save Error";

	public MenuController(Frame parent, Presentation presentation, PresentationFactory presentationFactory) {
		this.parent = parent;
		this.presentation = presentation;
		this.presentationFactory = presentationFactory;
		
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(createMenuItem(OPEN, onFileOpen));
		fileMenu.add(createMenuItem(NEW, onNewPresentation));
		fileMenu.add(createMenuItem(SAVE, onSave));
		fileMenu.addSeparator();
		fileMenu.add(createMenuItem(EXIT, onExit));
		add(fileMenu);
		
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(createMenuItem(NEXT, onNext));
		viewMenu.add(createMenuItem(PREV, onPrevious));
		viewMenu.add(createMenuItem(GOTO, onGoTo));
		add(viewMenu);

		Menu helpMenu = new Menu(HELP);
		helpMenu.add(createMenuItem(ABOUT, onAbout));
		setHelpMenu(helpMenu);		// nodig for portability (Motif, etc.).
	}

	// een menu-item aanmaken
	public MenuItem createMenuItem(String name, ActionListener handler) {
		MenuItem item = new MenuItem(name, new MenuShortcut(name.charAt(0)));
		item.addActionListener(handler);
		return item;
	}

	private ActionListener onNewPresentation = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			presentation.clear();
			parent.repaint();
		}
	};
	
	private ActionListener onFileOpen = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			try {
				presentation = presentationFactory.readPresentation(TEST_FILE);
				// TODO: Let the parent know
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(parent, IO_EXCEPTION + exc, LOAD_ERROR, JOptionPane.ERROR_MESSAGE);
			}
			parent.repaint();
		}
	};

	private ActionListener onSave = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				presentationFactory.writePresentation(presentation, SAVE_FILE);
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(parent, IO_EXCEPTION + exc, SAVE_ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}
	};

	private ActionListener onExit = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			presentation.exit(0);
		}
	};
	
	private ActionListener onNext = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			presentation.nextSlide();
		}
	};
	
	private ActionListener onPrevious = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			presentation.prevSlide();
		}
	};
	
	private ActionListener onGoTo = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			String pageNumberStr = JOptionPane.showInputDialog(PAGE_NR);
			int pageNumber = Integer.parseInt(pageNumberStr);
			presentation.setSlideNumber(pageNumber - 1);
		}
	};
	
	private ActionListener onAbout = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			AboutBox.show(parent);
		}
	};
}
