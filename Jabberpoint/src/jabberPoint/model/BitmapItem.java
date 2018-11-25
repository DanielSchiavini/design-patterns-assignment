package jabberPoint.model;
import java.io.File;

public class BitmapItem extends SlideItem {
  private File file;
  private String imageName;
  
  	// level staat voor het item-level; name voor de naam van het bestand met de afbeelding
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		file = new File(imageName);
	}

	// geef de bestandsnaam van de afbeelding
	public String getName() {
		return imageName;
	}
	
	public File getFile() {
		return file;
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}
