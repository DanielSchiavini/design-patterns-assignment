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


public class PresentationView extends JComponent implements Observer<Slide> {

	private Presentation presentation = null; // de presentatie
	private SlideView slideView = null;
	private int width;
	private int height;
	private SlideViewFactory slideViewFactory;
	
	private static final long serialVersionUID = 227L;
	
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;
	private Font labelFont = new Font("Dialog", Font.BOLD, 10); // het font voor labels


	public PresentationView(Presentation presentation, SlideViewFactory slideViewFactory, int width, int height) {
		setBackground(BGCOLOR); 
		this.presentation = presentation;
		this.slideViewFactory = slideViewFactory;
		this.width = width;
		this.height = height;
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public void update(Slide slide) {
		this.slideView = slideViewFactory.getSlideView(slide);
		repaint();
	}

	// teken de slide
	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setFont(labelFont);
		g.setColor(COLOR);
		String pageCounter = String.format("Slide %s of %d", 1 + presentation.getSlideNumber(), presentation.getSize());
		g.drawString(pageCounter, XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		slideView.draw(g, area, this);
	}
}
