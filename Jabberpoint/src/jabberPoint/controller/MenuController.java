package jabberPoint.controller;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.function.BiConsumer;

import javax.swing.JOptionPane;

import jabberPoint.model.Presentation;
import jabberPoint.model.factories.PresentationFactory;

/** <p>De controller voor het menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class MenuController extends MenuBar {
	
	private Presentation presentation; // Er worden commando's gegeven aan de presentatie
	PresentationFactory presentationFactory;
	private BiConsumer<String, IOException> onIOException;

	private static final long serialVersionUID = 227L;
	
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String ABOUT = "About";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGE_NR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String TEST_FILE = "test.xml";
	protected static final String SAVE_FILE = "dump.xml";
	
	protected static final String LOAD_ERROR = "Load Error";
	protected static final String SAVE_ERROR = "Save Error";

	public MenuController(Presentation presentation, ActionListener onAboutRequested,
			BiConsumer<String, IOException> onIOException, PresentationFactory presentationFactory) {
		this.presentation = presentation;
		this.presentationFactory = presentationFactory;
		this.onIOException = onIOException;
		
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(createMenuItem(OPEN, e -> openPresentation()));
		fileMenu.add(createMenuItem(NEW, e -> presentation.clear()));
		fileMenu.add(createMenuItem(SAVE, e -> savePresentation()));
		fileMenu.addSeparator();
		fileMenu.add(createMenuItem(EXIT, e -> presentation.exit(0)));
		add(fileMenu);
		
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(createMenuItem(NEXT, e -> presentation.nextSlide()));
		viewMenu.add(createMenuItem(PREV, e -> presentation.prevSlide()));
		viewMenu.add(createMenuItem(GOTO, e -> goToSlide()));
		add(viewMenu);

		Menu helpMenu = new Menu(HELP);
		helpMenu.add(createMenuItem(ABOUT, onAboutRequested));
		setHelpMenu(helpMenu);		// nodig for portability (Motif, etc.).
	}

	// een menu-item aanmaken
	public MenuItem createMenuItem(String name, ActionListener handler) {
		MenuItem item = new MenuItem(name, new MenuShortcut(name.charAt(0)));
		item.addActionListener(handler);
		return item;
	}

	public void openPresentation() {
		try {
			presentation = presentationFactory.readPresentation(TEST_FILE);
		} catch (IOException exc) {
			onIOException.accept(LOAD_ERROR, exc);
		}
	}

	private void savePresentation() {
		try {
			presentationFactory.writePresentation(presentation, SAVE_FILE);
		} catch (IOException exc) {
			onIOException.accept(SAVE_ERROR, exc);
		}
	};

	public void goToSlide() {
		String pageNumberStr = JOptionPane.showInputDialog(PAGE_NR);
		int pageNumber = Integer.parseInt(pageNumberStr);
		presentation.setSlideNumber(pageNumber - 1);
	}
}
