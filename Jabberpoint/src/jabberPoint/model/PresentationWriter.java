package jabberPoint.model;
import java.util.Vector;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.Presentation;
import jabberPoint.model.Slide;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TableOfContentsSlide;
import jabberPoint.model.TextItem;


public class PresentationWriter {
	
    protected static final String UNKNOWN_TYPE = "Unknown Element type %s!\n";
    
	/**
	 * Saves a presentation into a XML file.
	 * @param presentation - The presentation to be saved.
	 * @param filename - The path to the file.
	 */
	public void saveFile(Presentation presentation, String filename) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(filename));
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");
		out.print("<showtitle>");
		out.print(presentation.getTitle());
		out.println("</showtitle>");
		for (int slideNumber=0; slideNumber<presentation.getSize(); slideNumber++) {
			Slide slide = presentation.getSlide(slideNumber);
			if (slide instanceof TableOfContentsSlide) {
				out.println("<toc>");
				out.println("<title>" + slide.getTitle() + "</title>");
				out.println("</toc>");
				
			} else if (slide instanceof ContentSlide) {
				out.println("<slide>");
				out.println("<title>" + slide.getTitle() + "</title>");
				
				String subject = ((ContentSlide)slide).getSubject();
				if (subject != null && !subject.isEmpty()) {
					out.println("<subject>" + subject + "</subject>");
				}
				
				Vector<SlideItem> slideItems = slide.getSlideItems();
				for (int itemNumber = 0; itemNumber<slideItems.size(); itemNumber++) {
					SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
					writeSlideItem(out, slideItem);
				}
				out.println("</slide>");
			} else if (slide != null) {
				System.err.printf(UNKNOWN_TYPE, slide.getClass().getName());
			}
		}
		out.println("</presentation>");
		out.close();
	}

	/**
	 * Writes the slide item.
	 * Note: Only text and bitmap slide items that are written; any other types are ignored.  
	 * @param out - The print writer.
	 * @param slideItem - The slide item to be written.
	 */
	private void writeSlideItem(PrintWriter out, SlideItem slideItem) {
		out.print("<item kind="); 
		if (slideItem instanceof TextItem) {
			out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
			out.print( ( (TextItem) slideItem).getText());
		}
		else if (slideItem instanceof BitmapItem) {
			out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
			out.print( ( (BitmapItem) slideItem).getName());
		}
		else {
			System.err.printf(UNKNOWN_TYPE, slideItem.getClass().getName());
		}
		out.println("</item>");
	}
}
