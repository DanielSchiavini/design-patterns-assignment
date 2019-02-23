package jabberPoint.model;

/**
 * The abstract class from which the different slide item types should inherit.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
*/
public abstract class SlideItem {
	/** The level of the slide item **/
	private int level = 0;

	/**
	 * Creates a slide item.
	 * @param level: The style level of the item (0 for the highest level).
	 */
	public SlideItem(int level) {
		this.level = level;
	}

	/** Gets the level of this slide item **/
	public int getLevel() {
		return level;
	}

	/** Gets the name of the slide type **/
	public abstract String getSlideType();

	/** Gets the slide item as a String **/
	public abstract String getStringContents();
}
