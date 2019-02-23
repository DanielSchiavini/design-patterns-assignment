package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Test;

import jabberPoint.model.Presentation;
import jabberPoint.model.PresentationFileReader;
import jabberPoint.model.PresentationReader;
import jabberPoint.model.PresentationWriter;
import jabberPoint.model.factories.SlideFactory;

public class PresentationWriterTest {

	Presentation presentation = new Presentation();
	SlideFactory slideFactory = new SlideFactory();

	@After
	public void tearDown() throws Exception {
		File testFile = new File("test-save-file.xml");
		if (testFile.exists()) {
			testFile.delete();
		}
	}

	@Test
	public void testSaveFile() throws Exception {
		PresentationReader reader = new PresentationFileReader(presentation, "test.xml", slideFactory);
		reader.read();

		PresentationWriter writer = new PresentationWriter(presentation, "test-save-file.xml", slideFactory);
		writer.write();

		BufferedReader expectedReader = new BufferedReader(new FileReader("test.xml"));
		BufferedReader resultReader = new BufferedReader(new FileReader("test-save-file.xml"));
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
