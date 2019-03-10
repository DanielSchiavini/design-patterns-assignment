package jabberPoint.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

import jabberPoint.model.Presentation;
import jabberPoint.model.Slide;
import jabberPoint.view.factories.SlideViewFactory;
import util.Observer;


/**
 * The presentation view is responsible for displaying the presentation. 
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class PresentationView extends JComponent implements Observer<Slide> {

	private static final long serialVersionUID = 227L;
	
	/** The presentation to display **/
	private Presentation presentation;

	/** The class responsible for creating slide views **/
	private SlideViewFactory slideViewFactory;
	
	/** The view responsible for showing the current slide **/
	private SlideView slideView;
	
	/** The base width of the presentation, excluding scaling **/
	private int preferredWidth;

	/** The base height of the presentation, excluding scaling **/
	private int preferredHeight;

	// design constants
	private static final Color BACKGROUND_COLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final int X_POSITION = 1100;
	private static final int Y_POSITION = 20;
	private static final Font LABEL_FONT = new Font("Dialog", Font.BOLD, 10); // het font voor labels

	/**
	 * Creates a presentation view.
	 * @param presentation: The presentation instance.
	 * @param slideViewFactory: The class responsible for creating slide views.
	 * @param preferredWidth: The base width, excluding scaling.
	 * @param preferredHeight: The base height, excluding scaling.
	 */
	public PresentationView(Presentation presentation, SlideViewFactory slideViewFactory,
			int preferredWidth, int preferredHeight) {
		setBackground(BACKGROUND_COLOR); 
		this.presentation = presentation;
		this.slideViewFactory = slideViewFactory;
		this.preferredWidth = preferredWidth;
		this.preferredHeight = preferredHeight;
	}

	/**
	 * Gets the preferred size of the presentation.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(preferredWidth, preferredHeight);
	}

	/**
	 * Implementation of the observer pattern.
	 * Updates the presentation view when the slide changes.
	 * @param slide: The new slide.
	 */
	@Override
	public void update(Slide slide) {
		this.slideView = slideViewFactory.getSlideView(slide);
		repaint();
	}

	/**
	 * Draws the presentation.
	 * @param g: The graphics instance.
	 */
	public void paintComponent(Graphics g) {
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setFont(LABEL_FONT);
		g.setColor(COLOR);
		String pageCounter = String.format("Slide %s of %d", 1 + presentation.getSlideNumber(), presentation.getSize());
		g.drawString(pageCounter, X_POSITION, Y_POSITION);
		Rectangle area = new Rectangle(0, Y_POSITION, getWidth(), (getHeight() - Y_POSITION));
		slideView.draw(g, area, this);
	}
}
