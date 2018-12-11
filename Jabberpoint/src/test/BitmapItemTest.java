package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jabberPoint.model.BitmapItem;

public class BitmapItemTest {

	BitmapItem item = null;
	
	@Before
	public void setUp() throws Exception {
		item = new BitmapItem(1, "JabberPoint.jpg");
	}

	@Test
	public void testGetName() {
		assertEquals("JabberPoint.jpg", item.getName());
	}

	@Test
	public void testGetFile() {
		assertEquals("JabberPoint.jpg", item.getFile().getName());
	}

	@Test
	public void testToString() {
		String expected = "BitmapItem[1,JabberPoint.jpg]";
		String result = item.toString();
		assertEquals(expected, result);
	}

}
