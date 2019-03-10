package jabberPoint.model;

import java.io.IOException;

public interface PresentationReader {
	/**
	 * Reads a presentation.
	 * @throws IOException: If any I/O exception occurs.
	 */
	public Presentation read() throws IOException;
}
