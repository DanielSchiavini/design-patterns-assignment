package jabberPoint.model;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import jabberPoint.model.Presentation;
import jabberPoint.model.Slide;
import jabberPoint.model.factories.SlideFactory;


/**
 * Class responsible for writing a presentation to a XML file.
 * @author Daniel Schiavini
 */
public class PresentationWriter {
	// The presentation being written.
	private Presentation presentation;

	// The object responsible for creating slide writers.
	private SlideFactory slideFactory;

	// The name of the file that will be written.
	private String fileName;

	/**
	 * Creates a new presentation writer.
	 * @param presentation - The presentation to be saved.
	 * @param fileName: The name of the file to be read.
	 * @param slideFactory: The object responsible for creating slide writers.
	 */
	public PresentationWriter(Presentation presentation, String fileName, SlideFactory slideFactory) {
		this.presentation = presentation;
		this.slideFactory = slideFactory;
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
		out.println("<presentation>");
		out.print("<showtitle>");
		out.print(presentation.getTitle());
		out.println("</showtitle>");
		writeSlides(presentation, out);
		out.println("</presentation>");
		out.close();
	}

	/**
	 * Writes all the slides to the output.
	 * @param presentation: The presentation.
	 * @param out: The printer writer.
	 */
	private void writeSlides(Presentation presentation, PrintWriter out) {
		for (int slideNumber=0; slideNumber < presentation.getSize(); slideNumber++) {
			Slide slide = presentation.getSlide(slideNumber);
			SlideWriter writer = slideFactory.createWriter(slide);
			if (writer != null) {
				writer.write(out);
			}
		}
	}
}
