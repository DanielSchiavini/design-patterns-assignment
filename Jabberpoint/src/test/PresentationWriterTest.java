package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jabberPoint.model.Presentation;
import jabberPoint.model.PresentationReader;
import jabberPoint.model.PresentationWriter;

public class PresentationWriterTest {

	PresentationReader reader;
	PresentationWriter writer;
	Presentation presentation = null;

	@Before
	public void setUp() throws Exception {
		reader = new PresentationReader();
		writer = new PresentationWriter();
		presentation = new Presentation();
	}

	@After
	public void tearDown() throws Exception {
		File testFile = new File("test-save-file.xml");
		if (testFile.exists()) {
			testFile.delete();
		}
	}

	@Test
	public void testSaveFile() throws Exception {
		reader.loadFile(presentation, "test.xml");
		writer.saveFile(presentation, "test-save-file.xml");

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
