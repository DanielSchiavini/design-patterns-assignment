package test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import jabberPoint.model.TableOfContentsSlide;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TextItem;
import jabberPoint.model.factories.SlideFactory;
import jabberPoint.model.Presentation;
import jabberPoint.model.PresentationReader;
import jabberPoint.model.DemoPresentationReader;

public class TableOfContentsSlideTest {

	TableOfContentsSlide slide = null;
	Presentation presentation = null;
	SlideFactory slideFactory = new SlideFactory();
	
	@Before
	public void setUp() throws Exception {
		presentation = new Presentation();
		PresentationReader reader = new DemoPresentationReader(presentation, slideFactory);
		reader.read();
		slide = (TableOfContentsSlide)presentation.getSlide(0);
	}

	public void testBeforeGeneration() {
		assertEquals(0, slide.getSlideItems().size());
	}

	@Test
	public void testPrepareSlide0() {
		Vector<SlideItem> slideItems = slide.getSlideItems();
		assertEquals(3, slideItems.size());

		TextItem item = (TextItem)slideItems.elementAt(0);
		assertEquals("• JabberPoint", item.getText());
		assertEquals(1, item.getLevel());

		item = (TextItem)slideItems.elementAt(1);
		assertEquals("• Demonstratie van levels en stijlen", item.getText());
		assertEquals(2, item.getLevel());

		item = (TextItem)slideItems.elementAt(2);
		assertEquals("• De derde slide", item.getText());
		assertEquals(2, item.getLevel());
	}

	@Test
	public void testPrepareSlide3() {
		slide = (TableOfContentsSlide)presentation.getSlide(3);
		Vector<SlideItem> slideItems = slide.getSlideItems();
		assertEquals(3, slideItems.size());

		TextItem item = (TextItem)slideItems.elementAt(0);
		assertEquals("• JabberPoint", item.getText());
		assertEquals(2, item.getLevel());

		item = (TextItem)slideItems.elementAt(1);
		assertEquals("• Demonstratie van levels en stijlen", item.getText());
		assertEquals(2, item.getLevel());

		item = (TextItem)slideItems.elementAt(2);
		assertEquals("• De derde slide", item.getText());
		assertEquals(1, item.getLevel());
	}
}
