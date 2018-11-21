package test;

import static org.junit.Assert.*;

import java.awt.Font;

import org.junit.Test;

import jabberPoint.model.Style;

public class StyleTest {

	@Test
	public void testCreateStyles() {
		Style.createStyles();
		
		assertEquals("[0,java.awt.Color[r=255,g=0,b=0]; 48 on 20]", Style.getStyle(0).toString());
		assertEquals("[20,java.awt.Color[r=0,g=0,b=255]; 40 on 10]", Style.getStyle(1).toString());
		assertEquals("[50,java.awt.Color[r=0,g=0,b=0]; 36 on 10]", Style.getStyle(2).toString());
		assertEquals("[70,java.awt.Color[r=0,g=0,b=0]; 30 on 10]", Style.getStyle(3).toString());
		assertEquals("[90,java.awt.Color[r=0,g=0,b=0]; 24 on 10]", Style.getStyle(4).toString());
		assertEquals("[90,java.awt.Color[r=0,g=0,b=0]; 24 on 10]", Style.getStyle(5).toString());
	}
	
	@Test
	public void testGetFont() {
		int[] expectedSize = {48, 40, 36, 30, 24, 24};
		for (int i = 0; i <= 5; ++i) {
			Style style = Style.getStyle(i);
			Font font = style.getFont(1.0F);
			assertEquals("Helvetica", font.getName());
			assertTrue(font.isBold());
			assertEquals(expectedSize[i], font.getSize());
		}
	}
}
