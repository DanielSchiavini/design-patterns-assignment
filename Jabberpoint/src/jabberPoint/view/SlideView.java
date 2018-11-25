package jabberPoint.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import jabberPoint.model.Slide;
import jabberPoint.model.SlideItem;
import jabberPoint.model.Style;
import jabberPoint.view.factories.SlideItemViewFactory;

public class SlideView {
	private static SlideItemViewFactory itemFactory = SlideItemViewFactory.getInstance();
	
	private Slide slide;
	private int width;
	private int height;

	public SlideView(Slide slide, int width, int height) {
		this.slide = slide;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
	    int y = area.y;
		/* De titel hoeft niet meer apart behandeld te worden */
	    SlideItem item = slide.getTitleItem();
	    Style style = Style.getStyle(item.getLevel());
	    SlideItemView itemView = itemFactory.getItemView(item);
	    itemView.draw(area.x, y, scale, g, style, view);
	    y += itemView.getBoundingBox(g, view, scale, style).height;
	    for (int number=0; number < slide.getSize(); number++) {
	    	item = (SlideItem)slide.getSlideItems().elementAt(number);
		    itemView = itemFactory.getItemView(item);
	    	style = Style.getStyle(item.getLevel());
	      	itemView.draw(area.x, y, scale, g, style, view);
	    	y += itemView.getBoundingBox(g, view, scale, style).height;
	    }
	}
	
	// geef de schaal om de slide te kunnen tekenen
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)width), ((float)area.height) / ((float)height));
	}
	
}
