package jabberPoint.model.factories;

import java.io.IOException;

import jabberPoint.model.DemoPresentation;
import jabberPoint.model.Presentation;
import jabberPoint.model.PresentationReader;
import jabberPoint.model.PresentationWriter;

/**
 * The presentation factory is responsible for creating the presentation class.
 * @author Daniel Schiavini.
 */
public class PresentationFactory {
	/** We currently use a single presentation instance through the lifetime of the application **/
	private Presentation presentation;

	/**
	 * Creates a new presentation factory.
	 */
	public PresentationFactory() {
		this.presentation = new Presentation();
	}
	
	/**
	 * Gets the demonstration presentation.
	 * @return The presentation.
	 */
	public Presentation getDemoPresentation() {
		presentation.clear();
		new DemoPresentation().load(presentation);
		return presentation;
	}

	/**
	 * Reads a presentation from a file.
	 * @param fileName: The name of the file to read.
	 * @return The presentation.
	 * @throws IOException: If something went wrong reading the file.
	 */
	public Presentation readPresentation(String fileName) throws IOException {
		presentation.clear();
		new PresentationReader().loadFile(presentation, fileName);
		return presentation;
	}

	/**
	 * Writes the presentation to a file.
	 * @param presentation: The presentation to write.
	 * @param fileName: The name of the file where the presentation should be written to.
	 * @throws IOException: If something went wrong writing the file.
	 */
	public void writePresentation(Presentation presentation, String fileName) throws IOException {
		PresentationWriter presentationWriter = new PresentationWriter();
		presentationWriter.saveFile(presentation, fileName);
	}
}
