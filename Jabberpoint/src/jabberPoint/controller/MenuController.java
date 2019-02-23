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
import jabberPoint.model.PresentationReader;
import jabberPoint.model.PresentationWriter;
import jabberPoint.model.factories.PresentationFactory;

/** 
 * The menu controller, responsible for creating the menu and handling menu clicks.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class MenuController extends MenuBar {

	private static final long serialVersionUID = 227L;

	// instances to the dependency attributes
	private Presentation presentation;
	private PresentationFactory presentationFactory;
	private BiConsumer<String, IOException> onIOException;
	
	// string constants for the menu items
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
	
	// string constants for the reading and writing of files
	protected static final String TEST_FILE = "test.xml";
	protected static final String SAVE_FILE = "dump.xml";
	protected static final String LOAD_ERROR = "Load Error";
	protected static final String SAVE_ERROR = "Save Error";

	/**
	 * Constructs a new menu controller
	 * @param presentation: An instance to the presentation
	 * @param onAboutRequested: Handler to show the about box.
	 * @param onIOException: Handler to show an IO exception.
	 * @param presentationFactory: The presentation factory so new presentations can be loaded.
	 */
	public MenuController(Presentation presentation, ActionListener onAboutRequested,
			BiConsumer<String, IOException> onIOException, PresentationFactory presentationFactory) {
		this.presentation = presentation;
		this.presentationFactory = presentationFactory;
		this.onIOException = onIOException;

		// create the menus
		add(this.createFileMenu());
		add(this.createViewMenu());
		setHelpMenu(this.createHelpMenu(onAboutRequested));
	}
	
	/**
	 * Creates the 'file' menu.
	 * @return: The created menu.
	 */
	private Menu createFileMenu() {
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(createMenuItem(OPEN, e -> openPresentation()));
		fileMenu.add(createMenuItem(NEW, e -> presentation.clear()));
		fileMenu.add(createMenuItem(SAVE, e -> savePresentation()));
		fileMenu.addSeparator();
		fileMenu.add(createMenuItem(EXIT, e -> System.exit(0)));
		return fileMenu;
	}
	
	/**
	 * Creates the 'view' menu. 
	 * @return: The created menu.
	 */
	private Menu createViewMenu() {
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(createMenuItem(NEXT, e -> presentation.showNextSlide()));
		viewMenu.add(createMenuItem(PREV, e -> presentation.showPreviousSlide()));
		viewMenu.add(createMenuItem(GOTO, e -> goToSlide()));
		return viewMenu;
	}
	
	/**
	 * Creates the help menu, which is necessary for portability.
	 * @param onAboutRequested: Handler to show the about box.
	 * @return: The created menu.
	 */
	private Menu createHelpMenu(ActionListener onAboutRequested) {
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(createMenuItem(ABOUT, onAboutRequested));
		return helpMenu;
	}

	/**
	 * Creates a menu item.
	 * @param name: The name of the menu item.
	 * @param handler: The handler for when the menu is clicked.
	 * @return: The created menu item.
	 */
	private MenuItem createMenuItem(String name, ActionListener handler) {
		MenuItem item = new MenuItem(name, new MenuShortcut(name.charAt(0)));
		item.addActionListener(handler);
		return item;
	}

	/**
	 * Opens a presentation.
	 */
	public void openPresentation() {
		try {
			PresentationReader reader = presentationFactory.getPresentationReader(TEST_FILE);
			presentation = reader.read();
		} catch (IOException exc) {
			onIOException.accept(LOAD_ERROR, exc);
		}
	}

	/**
	 * Saves the currently opened presentation.
	 */
	private void savePresentation() {
		try {
			PresentationWriter writer = presentationFactory.getPresentationWriter(presentation, SAVE_FILE);
			writer.write();
		} catch (IOException exc) {
			onIOException.accept(SAVE_ERROR, exc);
		}
	};

	/**
	 * Shows an input dialog so the user can choose a slide number to go to.
	 */
	public void goToSlide() {
		String pageNumberStr = JOptionPane.showInputDialog(PAGE_NR);
		int pageNumber = Integer.parseInt(pageNumberStr);
		presentation.setSlideNumber(pageNumber - 1);
	}
}
