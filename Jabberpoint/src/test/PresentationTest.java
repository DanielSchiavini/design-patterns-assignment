package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jabberPoint.Accessor;
import jabberPoint.Presentation;
import jabberPoint.Slide;
import jabberPoint.Style;

public class PresentationTest {

	private Presentation presentation = null;
	
	@Before
	public void setUp() throws Exception {
		Style.createStyles();
		presentation = new Presentation();
		Accessor.getDemoAccessor().loadFile(presentation, "");
		presentation.setSlideNumber(0);
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
		int expected = 0;
		int result = presentation.getSlideNumber();
		assertEquals(expected, result);
	}

	@Test
	public void testSetSlideNumber() {
		int expected = 2;
		presentation.setSlideNumber(expected);
		int result = presentation.getSlideNumber();
		assertEquals(expected, result);
	}

	@Test
	public void testPrevSlide() {
		presentation.setSlideNumber(2);
		int expected = 1;
		presentation.prevSlide();
		int result = presentation.getSlideNumber();
		assertEquals(expected, result);
	}

	@Test
	public void testNextSlide() {
		int expected = 1;
		presentation.nextSlide();
		int result = presentation.getSlideNumber();
		assertEquals(expected, result);
	}

	@Test
	public void testClear() {
		int expected = 0;
		presentation = new Presentation();
		int result = presentation.getSize();
		assertEquals(expected, result);
	}

	@Test
	public void testAppend() {
		int expected = 4;
		presentation.append(presentation.getSlide(0));
		int result = presentation.getSize();
		assertEquals(expected, result);
	}

	@Test
	public void testGetSlide() {
		Slide expected = presentation.getSlide(0);
		presentation.append(expected);
		Slide result = presentation.getSlide(3);
		assertSame(expected, result);
	}

	@Test
	public void testGetCurrentSlide() {
		Slide expected = presentation.getSlide(0);
		Slide result = presentation.getCurrentSlide();
		assertSame(expected, result);
	}
}
