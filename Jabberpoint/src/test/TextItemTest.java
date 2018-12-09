package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import jabberPoint.model.Style;
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
	public void testGetAttributedString() {
		int indent = 0;
		Color color = Color.BLUE;
		int points = 5;
		int leading = 2;
		float scale = (float)3.0;
		Style style = new Style(color, points, indent, leading);
		AttributedString result = item.getAttributedString(style, scale);
		Font font = (Font)result.getIterator().getAttribute(TextAttribute.FONT);
		assertEquals("Helvetica", font.getName());
		assertTrue(font.isBold());
		assertEquals(15, font.getSize());
	}

	@Test
	public void testToString() {
		String expected = "TextItem[0,Test item]";
		String result = item.toString();
		assertEquals(expected, result);
	}

}
