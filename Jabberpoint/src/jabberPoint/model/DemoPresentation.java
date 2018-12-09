package jabberPoint.model;

/**
 * Class responsible for displaying a demonstration presentation.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class DemoPresentation {
	/**
	 * Loads the demo presentation into the given presentation instance.
	 * @param presentation: The presentation instance.
	 */
	public void load(Presentation presentation) {
		presentation.setTitle("Demo Presentation");
		presentation.append(new TableOfContentsSlide(presentation, "Inhoudsopgave"));
		presentation.append(createIntroductionSlide());
		presentation.append(createTextDemoSlide());
		presentation.append(new TableOfContentsSlide(presentation, "Overzicht"));
		presentation.append(createFinalSlide());
	}

	/**
	 * Creates an introduction slide.
	 * @return The created slide.
	 */
	private Slide createIntroductionSlide() {
		ContentSlide slide = new ContentSlide();
		slide.setTitle("JabberPoint");
		slide.append(1, "Het Java Presentatie Tool");
		slide.append(2, "Copyright (c) 1996-2000: Ian Darwin");
		slide.append(2, "Copyright (c) 2000-now:");
		slide.append(2, "Gert Florijn en Sylvia Stuurman");
		slide.append(4, "JabberPoint aanroepen zonder bestandsnaam");
		slide.append(4, "laat deze presentatie zien");
		slide.append(1, "Navigeren:");
		slide.append(3, "Volgende slide: PgDn of Enter");
		slide.append(3, "Vorige slide: PgUp of up-arrow");
		slide.append(3, "Stoppen: q or Q");
		return slide;
	}

	/**
	 * Creates a slide that demonstrates the level and styles.
	 * @return The created slide.
	 */
	private Slide createTextDemoSlide() {
		ContentSlide slide = new ContentSlide();
		slide.setTitle("Demonstratie van levels en stijlen");
		slide.append(1, "Level 1");
		slide.append(2, "Level 2");
		slide.append(1, "Nogmaals level 1");
		slide.append(1, "Level 1 heeft stijl nummer 1");
		slide.append(2, "Level 2 heeft stijl nummer 2");
		slide.append(3, "Zo ziet level 3 er uit");
		slide.append(4, "En dit is level 4");
		return slide;
	}

	/**
	 * Creates a slide that gives a final explanation of Jabberpoint.
	 * @return The created slide.
	 */
	private Slide createFinalSlide() {
		ContentSlide slide = new ContentSlide();
		slide.setTitle("De derde slide");
		slide.append(1, "Om een nieuwe presentatie te openen,");
		slide.append(2, "gebruik File->Open uit het menu.");
		slide.append(1, " ");
		slide.append(1, "Dit is het einde van de presentatie.");
		slide.append(new BitmapItem(1, "JabberPoint.jpg"));
		return slide;
	}
}
