package test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import jabberPoint.model.ContentSlide;
import jabberPoint.model.SlideItem;
import jabberPoint.model.TextItem;

public class ContentSlideTest {

	private ContentSlide slide = null;
	
	@Before
	public void setUp() throws Exception {
		slide = new ContentSlide(new TextItem(0, "Test Title"));
	}

	@Test
	public void testAppendItem() {
		SlideItem expected = new TextItem(0, null);
		slide.append(expected);
		SlideItem result = slide.getSlideItems().elementAt(0);
		assertSame(expected, result);
	}

	@Test
	public void testGetTitle() {
		String result = slide.getTitle();
		assertEquals("Test Title", result);
	}

	@Test
	public void testGetTitleItem() {
		TextItem result = slide.getTitleItem();
		assertEquals("Test Title", result.getText());
		assertEquals(0, result.getLevel());
	}

	@Test
	public void testGetSlideItem() {
		SlideItem expected = new TextItem(0, null);
		slide.append(expected);
		SlideItem result = slide.getSlideItems().elementAt(0);
		assertSame(expected, result);
	}

	@Test
	public void testGetSlideItemsInitial() {
		Vector<SlideItem> result = slide.getSlideItems();
		int expected = 0;
		assertEquals(expected, result.size());
	}

	@Test
	public void testGetSlideItemsAfterAppend() {
		String expectedText = "Some text";
		int expectedSize = 1;

		slide.append(new TextItem(1, expectedText));
		Vector<SlideItem> result = slide.getSlideItems();
		TextItem item = (TextItem)result.get(0);
		
		assertEquals(expectedSize, result.size());
		assertEquals(expectedText, item.getText());
	}

	@Test
	public void testGetSizeInitial() {
		int expected = 0;
		int result = slide.getSlideItems().size();
		assertEquals(expected, result);
	}

	@Test
	public void testGetSizeAfterAppend() {
		int expected = 1;
		slide.append(new TextItem(1, "Some text"));
		int result = slide.getSlideItems().size();
		assertEquals(expected, result);
	}

}
