package jabberPoint.model;

import java.io.PrintWriter;

/**
 * Interface for classes that are responsible for writing slides.
 * @author Daniel Schiavini
 */
public interface SlideWriter {
	/**
	 * Writes the slide to the output.
	 * @param out: The printer writer.
	 */
	void write(PrintWriter out);
}
