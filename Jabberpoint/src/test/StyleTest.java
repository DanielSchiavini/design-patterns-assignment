package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;

import org.junit.Before;
import org.junit.Test;

import jabberPoint.model.Style;
import jabberPoint.model.factories.StyleFactory;

public class StyleTest {

	StyleFactory styleFactory;
	
	@Before
	public void beforeEach() {
		styleFactory = new StyleFactory();
	}
	
	@Test
	public void testToString() {
		assertEquals("[0,java.awt.Color[r=255,g=0,b=0]; 48 on 20]", styleFactory.getStyle(0).toString());
		assertEquals("[20,java.awt.Color[r=0,g=0,b=255]; 40 on 10]", styleFactory.getStyle(1).toString());
		assertEquals("[50,java.awt.Color[r=0,g=0,b=0]; 36 on 10]", styleFactory.getStyle(2).toString());
		assertEquals("[70,java.awt.Color[r=0,g=0,b=0]; 30 on 10]", styleFactory.getStyle(3).toString());
		assertEquals("[90,java.awt.Color[r=0,g=0,b=0]; 24 on 10]", styleFactory.getStyle(4).toString());
		assertEquals("[90,java.awt.Color[r=0,g=0,b=0]; 24 on 10]", styleFactory.getStyle(5).toString());
	}
	
	@Test
	public void testFields() {
		int[] expectedSizes = {48, 40, 36, 30, 24, 24};
		int[] expectedLeftMargins = {0, 20, 50, 70, 90, 90};
		int[] expectedTopMargins = {20, 10, 10, 10, 10, 10};
		Color[] expectedColors = {Color.RED, Color.BLUE, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
		for (int i = 0; i <= 5; ++i) {
			Style style = styleFactory.getStyle(i);
			Font font = style.getFont(1.0F);
			assertEquals("Helvetica", font.getName());
			assertTrue(font.isBold());
			assertEquals(expectedSizes[i], font.getSize());
			assertEquals(expectedLeftMargins[i], style.getLeftMargin(1));
			assertEquals(expectedTopMargins[i], style.getTopMargin(1));
			assertEquals(expectedColors[i], style.getColor());
		}
	}
}
