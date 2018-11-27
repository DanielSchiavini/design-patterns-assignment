package jabberPoint.model;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.Presentation;
import jabberPoint.model.Slide;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TableOfContentsSlide;
import jabberPoint.model.TextItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/** XMLAccessor, reads and writes XML files
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 2018 Daniel Schiavini
 */

public class XMLAccessor extends Accessor {
	
    /** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";
    
    /** namen van xml tags of attributen */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDESUBJECT = "subject";
    protected static final String SLIDE = "slide";
    protected static final String TOC = "toc";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    
    /** tekst van messages */
    protected static final String UNKNOWN_TYPE = "Unknown Element type %s!\n";
    protected static final String INVALID_NUMBER = "Invalid number string %s!\n";
    
    /**
     * Reads a text element inside a XML node.
     * Note the first one will be returned if more than one element with this tag is found.
     * @param element - The XML element.
     * @param tagName - The tag to be read.
     * @return The text, if found, or null otherwise.
     */
    private String getText(Element element, String tagName) {
    	NodeList titles = element.getElementsByTagName(tagName);
    	if (titles.getLength() >= 1) {
    		return titles.item(0).getTextContent();
    	}
    	return null;
    }

    /**
     * Opens a XML file and loads the presentation data found in it.
     * Parsing errors are printed to the output but ignored otherwise.
     * @param presentation - The presentation where the data will be added to.
     * @param filename - The name of the file to be read.
     */
	public void loadFile(Presentation presentation, String filename) throws IOException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();    
			Document document = builder.parse(new File(filename)); // maak een JDOM document
			Element doc = document.getDocumentElement();
			presentation.setTitle(getText(doc, SHOWTITLE));

			NodeList nodeList = doc.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); ++i) {
				Node node = nodeList.item(i);
				if (node instanceof Element) {
					loadSlide(presentation, (Element)node);
				}
			}
		}
		catch (Exception ex) {
			throw new IOException(String.format("Cannot parse the file %s", filename), ex);
		}
	}

	/**
	 * Loads a slide and adds it to the presentation.
	 * @param presentation - The presentation where the slide will be added to.
	 * @param xmlNode - The XML node where the slide information can be found.
	 */
    private void loadSlide(Presentation presentation, Element xmlNode) {
		String nodeName = xmlNode.getNodeName();
		String title = getText(xmlNode, SLIDETITLE);
		if (nodeName == SLIDE) {
			ContentSlide slide = new ContentSlide();
			slide.setTitle(getText(xmlNode, SLIDETITLE));
			slide.setSubject(getText(xmlNode, SLIDESUBJECT));
			presentation.append(slide);

			NodeList slideItems = xmlNode.getElementsByTagName(ITEM);
			int maxItems = slideItems.getLength();
			for (int itemNumber = 0; itemNumber < maxItems; itemNumber++) {
				Element item = (Element) slideItems.item(itemNumber);
				loadSlideItem(slide, item);
			}
		} else if (nodeName == TOC) {
			presentation.append(new TableOfContentsSlide(presentation, title));
		} else {
			System.err.printf(UNKNOWN_TYPE, nodeName);
		}
    }

	/**
	 * Loads a slide item and adds it to the slide.
	 * @param presentation - The presentation where the slide will be added to.
	 * @param element - The XML element where the slide information can be found.
	 */
	protected void loadSlideItem(ContentSlide slide, Element item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String levelText = attributes.getNamedItem(LEVEL).getTextContent();
		if (levelText != null) {
			try {
				level = Integer.parseInt(levelText);
			}
			catch(NumberFormatException x) {
				System.err.printf(INVALID_NUMBER, levelText);
			}
		}
		
		String type = attributes.getNamedItem(KIND).getTextContent();
		if (TEXT.equals(type)) {
			slide.append(new TextItem(level, item.getTextContent()));
		}
		else if (IMAGE.equals(type)) {
			slide.append(new BitmapItem(level, item.getTextContent()));
		}
		else {
			System.err.printf(UNKNOWN_TYPE, type);
		}
	}

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
