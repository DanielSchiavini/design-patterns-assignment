package jabberPoint.model.factories;

import java.io.IOException;

import jabberPoint.model.DemoPresentation;
import jabberPoint.model.Presentation;
import jabberPoint.model.PresentationReader;
import jabberPoint.model.PresentationWriter;

public class PresentationFactory {
	private Presentation presentation;

	public PresentationFactory() {
		this.presentation = new Presentation();
	}
	
	public Presentation getDemoPresentation() {
		presentation.clear();
		new DemoPresentation().load(presentation);
		return presentation;
	}

	public Presentation readPresentation(String fileName) throws IOException {
		presentation.clear();
		new PresentationReader().loadFile(presentation, fileName);
		return presentation;
	}

	public void writePresentation(Presentation presentation, String fileName) throws IOException {
		PresentationWriter presentationWriter = new PresentationWriter();
		presentationWriter.saveFile(presentation, fileName);
	}
}
