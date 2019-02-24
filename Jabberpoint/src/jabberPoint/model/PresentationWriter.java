package jabberPoint.model;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import jabberPoint.model.Presentation;


/**
 * Class responsible for writing a presentation to a XML file.
 * @author Daniel Schiavini
 */
public class PresentationWriter {
	// The presentation being written.
	private Presentation presentation;

	// The name of the file that will be written.
	private String fileName;

	/**
	 * Creates a new presentation writer.
	 * @param presentation - The presentation to be saved.
	 * @param fileName: The name of the file to be read.
	 */
	public PresentationWriter(Presentation presentation, String fileName) {
		this.presentation = presentation;
		this.fileName = fileName;
	}

	/**
	 * Saves a presentation into a XML file.
	 * @param filename - The path to the file.
	 */
	public void write() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		presentation.writeXML(out);
		out.close();
	}
}
