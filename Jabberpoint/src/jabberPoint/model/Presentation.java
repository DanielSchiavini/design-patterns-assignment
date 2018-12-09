package jabberPoint.model;
import java.util.ArrayList;

import util.Observable;


/**
 * Class responsible for handling the presentation.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class Presentation extends Observable<Slide> {
	/** The title of the presentation **/
	private String showTitle;
	
	/** A list of the slides in the presentation **/
	private ArrayList<Slide> showList = null;
	
	/** The number of the current slide **/
	private int currentSlideNumber = 0;

	/**
	 * Creates a new presentation.
	 */
	public Presentation() {
		clear();
	}

	/**
	 * Gets the amount of items in the presentation.
	 * @return The amount of items.
	 */
	public int getSize() {
		return showList.size();
	}

	/**
	 * Gets the title of the presentation.
	 * @return The title of the presentation
	 */
	public String getTitle() {
		return showTitle;
	}

	/**
	 * Sets the title of the presentation.
	 * @param newTitle The new title of the presentation
	 */
	public void setTitle(String newTitle) {
		showTitle = newTitle;
	}

	/**
	 * Gets the number of the current slide.
	 * @return: The slide number.
	 */
	public int getSlideNumber() {
		return currentSlideNumber;
	}

	/**
	 * Changes the current slide and informs all observers.
	 * @param number: The number of the slide to be displayed.
	 */
	public void setSlideNumber(int number) {
		Slide slide = getSlide(number);
		currentSlideNumber = number;
		notifyObservers(slide);
	}

	/**
	 * Shows the previous slide, unless we are already showing the first slide.
	 */
	public void showPreviousSlide() {
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
	    }
	}

	/**
	 * Shows the next slide, unless we are already showing the last slide.
	 */
	public void showNextSlide() {
		if (currentSlideNumber < (showList.size()-1)) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}

	/**
	 * Resets the state of the presentation, removing all slide items.
	 * Also notifies the observers that no slide should be displayed.
	 */
	public void clear() {
		showList = new ArrayList<Slide>();
		currentSlideNumber = -1;
		setTitle("");
		this.notifyObservers(null);
	}

	/**
	 * Appends a new slide to the presentation.
	 * @param slide: The slide to be added.
	 */
	public void append(Slide slide) {
		showList.add(slide);
	}

	/**
	 * Gets the slide with the given number.
	 * @param number: The slide number.
	 * @return: The slide, if found, or null otherwise.
	 */
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()){
			return null;
	    }
		return (Slide)showList.get(number);
	}

	/**
	 * Gets the current slide.
	 * @return The slide.
	 */
	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}
}
