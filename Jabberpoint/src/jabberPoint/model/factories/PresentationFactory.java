package jabberPoint.model.factories;

import jabberPoint.model.DemoPresentationReader;
import jabberPoint.model.Presentation;
import jabberPoint.model.PresentationFileReader;
import jabberPoint.model.PresentationReader;
import jabberPoint.model.PresentationWriter;

/**
 * The presentation factory is responsible for creating the presentation class.
 * @author Daniel Schiavini.
 */
public class PresentationFactory {
	/** We currently use a single presentation instance through the lifetime of the application **/
	private Presentation presentation;

	// The class responsible for creating slides.
	private SlideFactory slideFactory;

	/**
	 * Creates a new presentation factory.
	 * @param slideFactory: The class responsible for creating slides.
	 */
	public PresentationFactory(SlideFactory slideFactory) {
		this.presentation = new Presentation();
		this.slideFactory = slideFactory;
	}

	/**
	 * Gets a presentation reader for a file.
	 * @param fileName: The name of the file to read.
	 * @return The presentation reader.
	 */
	public PresentationReader createReader(String fileName) {
		presentation.clear();
		if (fileName == null) {
			return new DemoPresentationReader(presentation, slideFactory);
		}
		return new PresentationFileReader(presentation, fileName, slideFactory);
	}

	/**
	 * Creates a class responsible for writing presentations.
	 * @param presentation: The presentation to write.
	 * @param fileName: The name of the file to write.
	 * @return The presentation writer.
	 */
	public PresentationWriter createWriter(Presentation presentation, String fileName) {
		return new PresentationWriter(presentation, fileName);
	}
}
