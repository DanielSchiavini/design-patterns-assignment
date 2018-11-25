package jabberPoint.view;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.Style;


public class BitmapItemView implements SlideItemView {
  private BitmapItem item;
  
// level staat voor het item-level; name voor de naam van het bestand met de afbeelding
	public BitmapItemView(BitmapItem item) {
		this.item = item;
	}

	// geef de bounding box van de afbeelding
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		BufferedImage bufferedImage = this.item.getImage();
		return new Rectangle((int) (myStyle.getIndent(scale)), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.getLeading(scale))) + 
				(int) (bufferedImage.getHeight(observer) * scale));
	}

	// teken de afbeelding
	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		BufferedImage bufferedImage = this.item.getImage();
		int width = x + (int) (myStyle.getIndent(scale));
		int height = y + (int) (myStyle.getLeading(scale));
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale),
                (int) (bufferedImage.getHeight(observer)*scale), observer);
	}
}
