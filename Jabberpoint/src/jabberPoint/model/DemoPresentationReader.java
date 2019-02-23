package jabberPoint.model;

import jabberPoint.model.factories.SlideFactory;

/**
 * Class responsible for creating a demonstration presentation.
 * @author Ian F. Darwin, Gert Florijn, Sylvia Stuurman, Daniel Schiavini
 */
public class DemoPresentationReader implements PresentationReader {
	// The presentation where the data will be added to.
	private final Presentation presentation;

	// The object responsible for creating slides.
	private final SlideFactory slideFactory;
	
	/**
	 * Creates a new demo presentation reader.
	 * @param presentation: The presentation where the data will be added to.
	 * @param slideFactory: The object responsible for creating slides
	 */
	public DemoPresentationReader(Presentation presentation, SlideFactory slideFactory) {
		this.presentation = presentation;
		this.slideFactory = slideFactory;
	}
	
	/**
	 * Loads the demo presentation into the given presentation instance.
	 * @param presentation: The presentation instance.
	 * @return 
	 */
	public Presentation read() {
		presentation.setTitle("Demo Presentation");
		presentation.append(slideFactory.createTableOfContents(presentation, "Inhoudsopgave"));
		presentation.append(createIntroductionSlide());
		presentation.append(createTextDemoSlide());
		presentation.append(slideFactory.createTableOfContents(presentation, "Overzicht"));
		presentation.append(createFinalSlide());
		return presentation;
	}

	/**
	 * Creates an introduction slide.
	 * @return The created slide.
	 */
	private Slide createIntroductionSlide() {
		ContentSlide slide = slideFactory.createContentSlide("JabberPoint", null);
		slide.append(slideFactory.createItem("text", 1, "Het Java Presentatie Tool"));
		slide.append(slideFactory.createItem("text", 2, "Copyright (c) 1996-2000: Ian Darwin"));
		slide.append(slideFactory.createItem("text", 2, "Copyright (c) 2000-now:"));
		slide.append(slideFactory.createItem("text", 2, "Gert Florijn en Sylvia Stuurman"));
		slide.append(slideFactory.createItem("text", 4, "JabberPoint aanroepen zonder bestandsnaam"));
		slide.append(slideFactory.createItem("text", 4, "laat deze presentatie zien"));
		slide.append(slideFactory.createItem("text", 1, "Navigeren:"));
		slide.append(slideFactory.createItem("text", 3, "Volgende slide: PgDn of Enter"));
		slide.append(slideFactory.createItem("text", 3, "Vorige slide: PgUp of up-arrow"));
		slide.append(slideFactory.createItem("text", 3, "Stoppen: q or Q"));
		return slide;
	}

	/**
	 * Creates a slide that demonstrates the level and styles.
	 * @return The created slide.
	 */
	private Slide createTextDemoSlide() {
		ContentSlide slide = slideFactory.createContentSlide("Demonstratie van levels en stijlen", null);
		slide.append(slideFactory.createItem("text", 1, "Level 1"));
		slide.append(slideFactory.createItem("text", 2, "Level 2"));
		slide.append(slideFactory.createItem("text", 1, "Nogmaals level 1"));
		slide.append(slideFactory.createItem("text", 1, "Level 1 heeft stijl nummer 1"));
		slide.append(slideFactory.createItem("text", 2, "Level 2 heeft stijl nummer 2"));
		slide.append(slideFactory.createItem("text", 3, "Zo ziet level 3 er uit"));
		slide.append(slideFactory.createItem("text", 4, "En dit is level 4"));
		return slide;
	}

	/**
	 * Creates a slide that gives a final explanation of Jabberpoint.
	 * @return The created slide.
	 */
	private Slide createFinalSlide() {
		ContentSlide slide = slideFactory.createContentSlide("De derde slide", null);
		slide.append(slideFactory.createItem("text",1, "Om een nieuwe presentatie te openen,"));
		slide.append(slideFactory.createItem("text",2, "gebruik File->Open uit het menu."));
		slide.append(slideFactory.createItem("text",1, " "));
		slide.append(slideFactory.createItem("text",1, "Dit is het einde van de presentatie."));
		slide.append(slideFactory.createItem("image", 1, "JabberPoint.jpg"));
		return slide;
	}
}
