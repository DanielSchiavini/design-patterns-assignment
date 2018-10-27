package test.fakes;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;

import jabberPoint.Presentation;
import jabberPoint.SlideViewerFrame;
import jabberPoint.SlideViewerComponent;

/**
 * Class that extends the slide viewer frame to expose get methods in tests.
 * @author Daniel
 */
public class FakeSlideViewerFrame extends SlideViewerFrame {
	private static final long serialVersionUID = 1L;

	private SlideViewerComponent slideViewerComponent;
	private String title;
	private Dimension size;
	private boolean visible;
	
	public FakeSlideViewerFrame(String title, Presentation presentation) {
		super(title, presentation);
	}

	public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
		super.setupWindow(slideViewerComponent, presentation);
		this.slideViewerComponent = slideViewerComponent;
	}
	
	public SlideViewerComponent getSlideViewerComponent() {
		return this.slideViewerComponent;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void addWindowListener(WindowAdapter adapter) {
		super.addWindowListener(adapter);
	}
	
	public Container getContentPane() {
		return super.getContentPane();
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public Dimension getSize() {
		return size;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getVisible() {
		return visible;
	}
}
