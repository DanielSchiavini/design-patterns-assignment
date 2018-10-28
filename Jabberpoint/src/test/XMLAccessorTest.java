package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jabberPoint.Presentation;
import jabberPoint.XMLAccessor;

public class XMLAccessorTest {

	XMLAccessor accessor = null;
	Presentation presentation = null;

	@Before
	public void setUp() throws Exception {
		accessor = new XMLAccessor();
		presentation = new Presentation();
	}

	@After
	public void tearDown() throws Exception {
	    File testFile = new File(".test-save-file.xml");
	    if (testFile.exists()) {
	       testFile.delete();     
	    }
	}

	@Test
	public void testLoadFile() throws IOException {
		accessor.loadFile(presentation, "test.xml");
		assertEquals(5, presentation.getSize());
		assertEquals("XML-Based Presentation for Jabberpoint", presentation.getTitle());
		assertEquals("Een bijna lege slide", presentation.getSlide(1).getTitle());
		assertEquals(null, presentation.getCurrentSlide());
		assertEquals("TextItem[2,Herschreven door]",
				presentation.getSlide(0).getSlideItem(2).toString());
		assertEquals("BitmapItem[1,JabberPoint.jpg]",
				presentation.getSlide(4).getSlideItem(4).toString());
	}

	@Test
	public void testSaveFile() throws Exception {
		accessor.loadFile(presentation, "test.xml");
		accessor.saveFile(presentation, ".test-save-file.xml");

		BufferedReader expectedReader = new BufferedReader(new FileReader("test.xml"));
		BufferedReader resultReader = new BufferedReader(new FileReader(".test-save-file.xml"));    
		try {
			String expectedLine = expectedReader.readLine();
			while (expectedLine != null) {
				String resultLine = resultReader.readLine();
				assertEquals(expectedLine.replaceAll("\\s+"," ").trim(),
						resultLine.replaceAll("\\s+"," ").trim());
				expectedLine = expectedReader.readLine();
			}
		}
		finally {
			expectedReader.close();
			resultReader.close();
		}
	}

}
