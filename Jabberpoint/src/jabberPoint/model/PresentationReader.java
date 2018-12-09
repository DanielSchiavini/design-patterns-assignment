package jabberPoint.model;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jabberPoint.model.BitmapItem;
import jabberPoint.model.Presentation;
import jabberPoint.model.TableOfContentsSlide;
import jabberPoint.model.TextItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Class responsible for reading a presentation from a XML file.
 * @author Daniel Schiavini
 */
public class PresentationReader {
	// names of XML tags
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
    
    // text for the error messages
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
		} else if (nodeName != SHOWTITLE) {
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
}
