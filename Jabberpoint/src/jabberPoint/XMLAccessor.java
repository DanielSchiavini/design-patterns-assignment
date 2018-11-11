package jabberPoint;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
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
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
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
		int slideNumber, max = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();    
			Document document = builder.parse(new File(filename)); // maak een JDOM document
			Element doc = document.getDocumentElement();
			presentation.setTitle(getText(doc, SHOWTITLE));

			NodeList slides = doc.getChildNodes();
			max = slides.getLength();
			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				loadSlide(presentation, slides.item(slideNumber));
			}
		} 
		catch (IOException iox) {
			System.err.println(iox.toString());
		}
		catch (SAXException sax) {
			System.err.println(sax.getMessage());
		}
		catch (ParserConfigurationException pcx) {
			System.err.println(PCE);
		}
		
	}

	/**
	 * Loads a slide and adds it to the presentation.
	 * @param presentation - The presentation where the slide will be added to.
	 * @param xmlNode - The XML node where the slide information can be found.
	 */
    private void loadSlide(Presentation presentation, Node xmlNode) {
		String nodeName = xmlNode.getNodeName();

		if (nodeName == SLIDE) {
			Element xmlSlide = (Element)xmlNode;
			ContentSlide slide = new ContentSlide();
			slide.setTitle(getText(xmlSlide, SLIDETITLE));
			slide.setSubject(getText(xmlSlide, SLIDESUBJECT));
			NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
			int maxItems = slideItems.getLength();
			for (int itemNumber = 0; itemNumber < maxItems; itemNumber++) {
				Element item = (Element) slideItems.item(itemNumber);
				loadSlideItem(slide, item);
			}
			presentation.append(slide);
		} else if (nodeName == TOC) {
			Element xmlSlide = (Element)xmlNode;
			TableOfContentsSlide slide = new TableOfContentsSlide(presentation);
			slide.setTitle(getText(xmlSlide, SLIDETITLE));
			presentation.append(slide);
		} else {
			return;
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
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println(NFE);
			}
		}
		String type = attributes.getNamedItem(KIND).getTextContent();
		if (TEXT.equals(type)) {
			slide.append(new TextItem(level, item.getTextContent()));
		}
		else {
			if (IMAGE.equals(type)) {
				slide.append(new BitmapItem(level, item.getTextContent()));
			}
			else {
				System.err.println(UNKNOWNTYPE);
			}
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
				writeTableOfContents(out, (TableOfContentsSlide)slide);
			} else if (slide instanceof ContentSlide ) {
				writeContentSlide(out, (ContentSlide)slide);
			}
		}
		out.println("</presentation>");
		out.close();
	}

	private void writeTableOfContents(PrintWriter out, TableOfContentsSlide slide) {
		out.println("<toc>");
		out.println("<title>" + slide.getTitle() + "</title>");
		out.println("</toc>");
	}

	private void writeContentSlide(PrintWriter out, ContentSlide slide) {
		out.println("<slide>");
		out.println("<title>" + slide.getTitle() + "</title>");
		String subject = slide.getSubject();
			if (subject != null && !subject.isEmpty()) {
				out.println("<subject>" + subject + "</subject>");
			}
			
			Vector<SlideItem> slideItems = slide.getSlideItems();
			for (int itemNumber = 0; itemNumber<slideItems.size(); itemNumber++) {
				SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
				writeSlideItem(out, slideItem);
				out.println("</item>");
			}
		out.println("</slide>");
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
			System.out.println("Ignoring " + slideItem);
		}
	}
}
