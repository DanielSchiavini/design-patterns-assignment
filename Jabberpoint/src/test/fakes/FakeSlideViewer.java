package test.fakes;

import jabberPoint.Presentation;
import jabberPoint.Slide;
import jabberPoint.SlideViewer;

/**
 * Class that extends the slide viewer frame to expose get methods in tests.
 * @author Daniel
 */
public class FakeSlideViewer implements SlideViewer {
	private Presentation presentation;
	private Slide currentSlide;
	
	@Override
	public void update(Presentation presentation, Slide currentSlide) {
		this.presentation = presentation;
		this.currentSlide = currentSlide;
	}

	public Presentation getPresentation() {
		return this.presentation;
	}
	
	public Slide getCurrentSlide() {
		return this.currentSlide;
	}
}
