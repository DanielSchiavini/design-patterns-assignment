package test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import jabberPoint.TableOfContentsSlide;
import jabberPoint.SlideItem;
import jabberPoint.TextItem;
import jabberPoint.Presentation;
import jabberPoint.DemoPresentation;

public class TableOfContentsSlideTest {

	TableOfContentsSlide slide = null;
	Presentation presentation = null;
	
	@Before
	public void setUp() throws Exception {
		presentation = new Presentation();
		new DemoPresentation().loadFile(presentation, "unusedFilename");
		slide = (TableOfContentsSlide)presentation.getSlide(0);
	}

	public void testBeforeGeneration() {
		assertEquals(0, slide.generate().getSlideItems().size());
	}

	@Test
	public void testGenerateItemsSlide0() {
		Vector<SlideItem> slideItems = slide.generate().getSlideItems();
		assertEquals(3, slideItems.size());

		TextItem item = (TextItem)slideItems.elementAt(0);
		assertEquals("JabberPoint", item.getText());
		assertEquals(1, item.getLevel());

		item = (TextItem)slideItems.elementAt(1);
		assertEquals("Demonstratie van levels en stijlen", item.getText());
		assertEquals(2, item.getLevel());

		item = (TextItem)slideItems.elementAt(2);
		assertEquals("De derde slide", item.getText());
		assertEquals(2, item.getLevel());
	}

	@Test
	public void testGenerateItemsSlide3() {
		slide = (TableOfContentsSlide)presentation.getSlide(3);
		Vector<SlideItem> slideItems = slide.generate().getSlideItems();
		assertEquals(3, slideItems.size());

		TextItem item = (TextItem)slideItems.elementAt(0);
		assertEquals("JabberPoint", item.getText());
		assertEquals(2, item.getLevel());

		item = (TextItem)slideItems.elementAt(1);
		assertEquals("Demonstratie van levels en stijlen", item.getText());
		assertEquals(2, item.getLevel());

		item = (TextItem)slideItems.elementAt(2);
		assertEquals("De derde slide", item.getText());
		assertEquals(1, item.getLevel());
	}

}
