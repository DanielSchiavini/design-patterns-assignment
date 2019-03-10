package jabberPoint.model;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jabberPoint.model.Presentation;
import jabberPoint.model.factories.SlideFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Class responsible for reading a presentation from a XML file.
 * @author Daniel Schiavini
 */
public class PresentationFileReader implements PresentationReader {
	// names of XML tags
	protected static final String SHOWTITLE = "showtitle";
	protected static final String SLIDETITLE = "title";
	protected static final String SLIDESUBJECT = "subject";
	protected static final String SLIDE = "slide";
	protected static final String TOC = "toc";
	protected static final String ITEM = "item";
	protected static final String LEVEL = "level";
	protected static final String KIND = "kind";
	
	// text for the error messages
	protected static final String UNKNOWN_TYPE = "Unknown Element type %s!\n";
	protected static final String INVALID_NUMBER = "Invalid number string %s!\n";

	// The presentation where the data will be added to.
	private final Presentation presentation;
	
	// The name of the file to be read.
	private final String fileName;

	// The object responsible for creating slides.
	private final SlideFactory slideFactory;

	/**
	 * Creates a new presentation file reader.
	 * @param presentation: The presentation where the data will be added to.
	 * @param fileName: The name of the file to be read.
	 * @param slideFactory: The object responsible for creating slides
	 * @param slideItemFactory: The object responsible for creating slide items.
	 */
	public PresentationFileReader(Presentation presentation, String fileName, SlideFactory slideFactory) {
		this.presentation = presentation;
		this.fileName = fileName;
		this.slideFactory = slideFactory;
	}
	
	/**
	 * Opens a XML file and loads the presentation data found in it.
	 * Parsing errors are printed to the output but ignored otherwise.
	 * @throws IOException: If any exception occurs.
	 */
	public Presentation read() throws IOException {
		try {
			Element doc = openDocument(fileName);
			presentation.setTitle(getText(doc, SHOWTITLE));

			NodeList nodeList = doc.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); ++i) {
				Node node = nodeList.item(i);
				if (node instanceof Element) {
					loadSlide(presentation, (Element)node);
				}
			}
			return presentation;
		}
		catch (Exception ex) {
			throw new IOException(String.format("Cannot parse the file %s", fileName), ex);
		}
	}

	/**
	 * Opens a document with the given file name.
	 * @param fileName: The name of the file.
	 * @return An XML document.
	 * @throws ParserConfigurationException: If a configuration error occurs.
	 * @throws SAXExceptio: If an XML error occurs.
	 * @throws IOException: If an IO exception occurs.
	 */
	private Element openDocument(String fileName) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
		Document document = builder.parse(new File(fileName)); // maak een JDOM document
		Element doc = document.getDocumentElement();
		return doc;
	}

	/**
	 * Loads a slide and adds it to the presentation.
	 * @param presentation: The presentation where the slide will be added to.
	 * @param xmlNode: The XML node where the slide information can be found.
	 */
	private void loadSlide(Presentation presentation, Element xmlNode) {
		String nodeName = xmlNode.getNodeName();
		String title = getText(xmlNode, SLIDETITLE);
		if (nodeName == SLIDE) {
			String subject = getText(xmlNode, SLIDESUBJECT);
			ContentSlide slide = slideFactory.createContentSlide(title, subject);
			presentation.append(slide);

			NodeList slideItems = xmlNode.getElementsByTagName(ITEM);
			int maxItems = slideItems.getLength();
			for (int itemNumber = 0; itemNumber < maxItems; itemNumber++) {
				Element item = (Element) slideItems.item(itemNumber);
				loadSlideItem(slide, item);
			}
		} else if (nodeName == TOC) {
			presentation.append(slideFactory.createTableOfContents(presentation, title));
		} else if (nodeName != SHOWTITLE) {
			System.err.printf(UNKNOWN_TYPE, nodeName);
		}
	}

	/**
	 * Loads a slide item and adds it to the slide.
	 * @param presentation: The presentation where the slide will be added to.
	 * @param element: The XML element where the slide information can be found.
	 */
	private void loadSlideItem(ContentSlide slide, Element item) {
		NamedNodeMap attributes = item.getAttributes();
		int level = parseIntAttribute(attributes.getNamedItem(LEVEL), 1);
		String type = attributes.getNamedItem(KIND).getTextContent();
		SlideItem slideItem = slideFactory.createItem(type, level, item.getTextContent());
		if (slideItem != null) {
			slide.append(slideItem);
		}
	}

	/**
	 * Tries to parse an integer from the attribute with the given name, returning the default value if it fails.
	 * @param attribute: The attribute.
	 * @param defaultValue: The default value to return if the integer cannot be parsed.
	 * @return The integer value of the attribute, or the default value.
	 */
	private int parseIntAttribute(Node attribute, int defaultValue) {
		String levelText = attribute.getTextContent();
		if (levelText != null) {
			try {
				return Integer.parseInt(levelText);
			}
			catch (NumberFormatException x) {
				System.err.printf(INVALID_NUMBER, levelText);
			}
		}
		return defaultValue;
	}

	/**
	 * Reads a text element inside a XML node.
	 * Note the first one will be returned if more than one element with this tag is found.
	 * @param element: The XML element.
	 * @param tagName: The tag to be read.
	 * @return The text, if found, or null otherwise.
	 */
	private String getText(Element element, String tagName) {
		NodeList titles = element.getElementsByTagName(tagName);
		if (titles.getLength() >= 1) {
			return titles.item(0).getTextContent();
		}
		return null;
	}
}
