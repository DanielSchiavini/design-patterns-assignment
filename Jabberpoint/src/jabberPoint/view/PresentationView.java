package jabberPoint.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;

import jabberPoint.model.Presentation;
import jabberPoint.model.Slide;
import jabberPoint.view.factories.SlideViewFactory;
import util.Observer;


public class PresentationView extends JComponent implements Observer<Slide> {

	private Font labelFont = null; // het font voor labels
	private Presentation presentation = null; // de presentatie
	private JFrame frame = null;
	private SlideView slideView = null;
	
	private static final long serialVersionUID = 227L;
	private static SlideViewFactory slideViewFactory = SlideViewFactory.getInstance();
	
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	private int width;
	private int height;

	public PresentationView(Presentation pres, JFrame frame, int width, int height) {
		setBackground(BGCOLOR); 
		presentation = pres;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
		this.width = width;
		this.height = height;
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public void update(Slide slide) {
		if (slide == null) {
			repaint();
			return;
		}
		this.slideView  = slideViewFactory.getSlideView(slide);
		repaint();
		frame.setTitle(presentation.getTitle());
	}

	// teken de slide
	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (presentation.getSlideNumber() < 0 || slideView == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
                 presentation.getSize(), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		slideView.draw(g, area, this);
	}
}
