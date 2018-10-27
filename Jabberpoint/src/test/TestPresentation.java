package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jabberPoint.Accessor;
import jabberPoint.Presentation;
import jabberPoint.SlideViewerComponent;
import jabberPoint.Style;
import test.fakes.FakeSlideViewerFrame;

public class TestPresentation {

	private Presentation presentation = null;
	private FakeSlideViewerFrame slideViewerFrame = null;
	private SlideViewerComponent slideViewerComponent = null;
	
	@Before
	public void setUp() throws Exception {
		Style.createStyles();
		presentation = new Presentation();
		slideViewerFrame = new FakeSlideViewerFrame("Jabberpoint test", presentation);
		slideViewerComponent = slideViewerFrame.getSlideViewerComponent();
		Accessor.getDemoAccessor().loadFile(presentation, "");
		presentation.setSlideNumber(0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSize() {
		int expected = 3;
		int result = presentation.getSize();
		assertEquals(expected, result);
	}

	@Test
	public void testGetTitle() {
		String expected = "Demo Presentation";
		String result = presentation.getTitle();
		assertEquals(expected, result);
	}

	@Test
	public void testSetTitle() {
		String expected = "A new title";
		presentation.setTitle(expected);
		String result = presentation.getTitle();
		assertEquals(expected, result);
	}

	@Test
	public void testGetSlideNumber() {
		// TODO
	}

	@Test
	public void testSetSlideNumber() {
		// TODO
	}

	@Test
	public void testPrevSlide() {
		// TODO
	}

	@Test
	public void testNextSlide() {
		// TODO
	}

	@Test
	public void testClear() {
		// TODO
	}

	@Test
	public void testAppend() {
		// TODO
	}

	@Test
	public void testGetSlide() {
		// TODO
	}

	@Test
	public void testGetCurrentSlide() {
		// TODO
	}

	@Test
	public void testExit() {
		// TODO
	}

}
