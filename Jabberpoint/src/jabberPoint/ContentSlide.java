package jabberPoint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

public class ContentSlide extends Slide {
	/* Geen String meer maar een TextItem */
	private Vector<SlideItem> items; // de slide-items worden in een Vector bewaard
	private String subject = null;

	public ContentSlide() {
		items = new Vector<SlideItem>();
	}

	// Voeg een SlideItem toe
	public void append(SlideItem anItem) {
		items.addElement(anItem);
	}

	// Maak een TextItem van String, en voeg het TextItem toe
	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	// Verwijder alle slide items
	protected void clear() {
		items.clear();
	}

	// geef het onderwerp van de slide
	public String getSubject() {
		return subject;
	}

	// verander het onderwerp van de slide
	public void setSubject(String subject) {
		/* Creëer nu een TextItem op basis van de nieuwe titel */
		this.subject  = subject;
	}

	// geef het betreffende SlideItem
	public SlideItem getSlideItem(int number) {
		return (SlideItem)items.elementAt(number);
	}

	// geef alle SlideItems in een Vector
	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	// geef de afmeting van de Slide
	public int getSize() {
		return items.size();
	}

	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
	    int y = area.y;
		/* De titel hoeft niet meer apart behandeld te worden */
	    SlideItem slideItem = new TextItem(0, getTitle());
	    Style style = Style.getStyle(slideItem.getLevel());
	    slideItem.draw(area.x, y, scale, g, style, view);
	    y += slideItem.getBoundingBox(g, view, scale, style).height;
	    for (int number=0; number<getSize(); number++) {
	      slideItem = (SlideItem)getSlideItems().elementAt(number);
	      style = Style.getStyle(slideItem.getLevel());
	      slideItem.draw(area.x, y, scale, g, style, view);
	      y += slideItem.getBoundingBox(g, view, scale, style).height;
	    }
	  }

	// geef de schaal om de slide te kunnen tekenen
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)WIDTH), ((float)area.height) / ((float)HEIGHT));
	}
}
