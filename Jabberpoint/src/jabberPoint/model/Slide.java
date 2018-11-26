package jabberPoint.model;
import java.util.Vector;

public abstract class Slide {
	private TextItem title;

	// geef de titel van de slide
	public String getTitle() {
		return title.getText();
	}

	// geef de titel slide item van de slide
	public TextItem getTitleItem() {
		return title;
	}

	// verander de titel van de slide
	public void setTitle(String newTitle) {
		/* Creëer nu een TextItem op basis van de nieuwe titel */
		title = new TextItem(0, newTitle);
	}

	// geef alle SlideItems in een Vector
	public abstract Vector<SlideItem> getSlideItems();
}
