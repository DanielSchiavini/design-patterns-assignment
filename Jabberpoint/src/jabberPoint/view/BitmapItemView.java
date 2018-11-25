package jabberPoint.view;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.Style;


public class BitmapItemView implements SlideItemView {
	private BufferedImage bufferedImage;
  
	// level staat voor het item-level; name voor de naam van het bestand met de afbeelding
	public BitmapItemView(BitmapItem item) {
		try {
			bufferedImage = ImageIO.read(item.getFile());
		} catch (IOException e) {
			System.err.printf("Cannot find image file %s\n", item.getName()) ;
		}
	}

	// geef de bounding box van de afbeelding
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		return new Rectangle((int) (myStyle.getIndent(scale)), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.getLeading(scale))) + 
				(int) (bufferedImage.getHeight(observer) * scale));
	}

	// teken de afbeelding
	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		int width = x + (int) (myStyle.getIndent(scale));
		int height = y + (int) (myStyle.getLeading(scale));
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale),
                (int) (bufferedImage.getHeight(observer)*scale), observer);
	}
}
