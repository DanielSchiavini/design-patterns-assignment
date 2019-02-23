package jabberPoint.model;

import java.io.PrintWriter;

public class TableOfContentsSlideWriter implements SlideWriter {
	// The slide being written.
	private TableOfContentsSlide slide;

	/**
	 * 
	 * @param slide: The slide to write.
	 */
	public TableOfContentsSlideWriter(TableOfContentsSlide slide) {
		this.slide = slide;
	}

	/**
	 * Writes a table of contents slide to the output.
	 * @param out: The printer writer.
	 */
	public void write(PrintWriter out) {
		out.println("<toc>");
		out.println("<title>" + slide.getTitle() + "</title>");
		out.println("</toc>");
	}
}
