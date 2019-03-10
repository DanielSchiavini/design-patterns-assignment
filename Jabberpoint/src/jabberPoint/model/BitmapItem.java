package jabberPoint.model;
import java.io.File;
import java.io.PrintWriter;

/**
 * Class responsible for displaying images.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class BitmapItem extends SlideItem {
	/** The image file **/
	private File file;
	
	/** The image file name **/
	private String imageName;
  
	/**
	 * Creates a new bitmap item. 
	 * @param level: The style level of the item (0 for the highest level).
	 * @param name: The file name.
	 */
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		file = new File(imageName);
	}
	
	/**
	 * Gets the name of the image file.
	 * @return The image name.
	 */
	public String getName() {
		return imageName;
	}
	
	/**
	 * Gets the image file.
	 * @return The image file.
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * Converts the bitmap item into a string.
	 */
	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}

	/**
	 * Writes the slide item to the output.
	 * @param out - The print writer.
	 */
	@Override
	public void writeXML(PrintWriter out)
	{
		out.print("<item kind=\"image\" level=\"" + getLevel() + "\">");
		out.print(imageName);
		out.println("</item>");
	}
}
