package test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import jabberPoint.ContentSlide;
import jabberPoint.SlideItem;
import jabberPoint.TextItem;

public class ContentSlideTest {

	ContentSlide slide = null;
	
	@Before
	public void setUp() throws Exception {
		slide = new ContentSlide();
	}

	@Test
	public void testAppendItem() {
		SlideItem expected = new TextItem();
		slide.append(expected);
		SlideItem result = slide.getSlideItem(0);
		assertSame(expected, result);
	}

	@Test
	public void testAppendText() {
		slide.append(1, "Some text");
		TextItem result = (TextItem)slide.getSlideItem(0);
		assertEquals(1, result.getLevel());
		assertEquals("Some text", result.getText());
	}

	@Test
	public void testSetGetTitle() {
		String expected = "Test Title";
		slide.setTitle(expected);
		String result = slide.getTitle();
		assertEquals(expected, result);
	}

	@Test
	public void testGetSlideItem() {
		SlideItem expected = new TextItem();
		slide.append(expected);
		SlideItem result = slide.getSlideItem(0);
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
		
		slide.append(1, expectedText);
		Vector<SlideItem> result = slide.getSlideItems();
		TextItem item = (TextItem)result.get(0);
		
		assertEquals(expectedSize, result.size());
		assertEquals(expectedText, item.getText());
	}

	@Test
	public void testGetSizeInitial() {
		int expected = 0;
		int result = slide.getSize();
		assertEquals(expected, result);
	}

	@Test
	public void testGetSizeAfterAppend() {
		int expected = 1;
		slide.append(1, "Some text");
		int result = slide.getSize();
		assertEquals(expected, result);
	}

}
