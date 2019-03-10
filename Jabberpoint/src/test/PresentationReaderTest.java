package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import jabberPoint.model.Presentation;
import jabberPoint.model.PresentationFileReader;
import jabberPoint.model.PresentationReader;
import jabberPoint.model.factories.SlideFactory;

public class PresentationReaderTest {

	private final Presentation presentation = new Presentation();
	private final SlideFactory slideFactory = new SlideFactory();

	@After
	public void tearDown() throws Exception {
		File testFile = new File("test-save-file.xml");
		if (testFile.exists()) {
			testFile.delete();
		}
	}

	@Test
	public void testLoadFile() throws IOException {
		PresentationReader reader = new PresentationFileReader(presentation, "test.xml", slideFactory);
		reader.read();
		assertEquals(7, presentation.getSize());
		assertEquals("XML-Based Presentation for Jabberpoint", presentation.getTitle());
		assertEquals("jabberPoint.model.TableOfContentsSlide",
				presentation.getSlide(0).getClass().getName());
		assertEquals("Een bijna lege slide", presentation.getSlide(2).getTitle());
		assertEquals(null, presentation.getCurrentSlide());
		assertEquals("TextItem[2,Herschreven door]",
				presentation.getSlide(1).getSlideItems().elementAt(2).toString());
		assertEquals("BitmapItem[1,JabberPoint.jpg]",
				presentation.getSlide(6).getSlideItems().elementAt(4).toString());
	}
}
