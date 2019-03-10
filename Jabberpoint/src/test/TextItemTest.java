package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jabberPoint.model.TextItem;

public class TextItemTest {

	TextItem item = null;
	
	@Before
	public void setUp() throws Exception {
		item = new TextItem(0, "Test item");
	}

	@Test
	public void testGetText() {
		assertEquals("Test item", item.getText());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(item.isEmpty());
	}

	@Test
	public void testEmpty() {
		assertTrue(new TextItem(0, null).isEmpty());
		assertTrue(new TextItem(0, "").isEmpty());
		assertEquals("", new TextItem(0, null).getText());
	}

	@Test
	public void testToString() {
		String expected = "TextItem[0,Test item]";
		String result = item.toString();
		assertEquals(expected, result);
	}

}
