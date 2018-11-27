package jabberPoint.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

public class MarshallAccessor extends Accessor {

	public String readFile(final File file) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(file));

	    try {
	        char[] buf = new char[1024];
	        int r = 0;
	        while ((r = reader.read(buf)) != -1) {
	            result.append(buf, 0, r);
	        }
	    }
	    finally {
	        reader.close();
	    }
	    return result.toString();
	}

	@Override
	public void loadFile(Presentation p, String fn) throws IOException {
		String xmlString = this.readFile(new File(fn));
		Presentation result = JAXB.unmarshal(new StringReader(xmlString), Presentation.class);
		p.setTitle(result.getTitle());
		for (int i = 0; i < result.getSize(); ++i) {
			p.append(result.getSlide(i));
		}
	}

	@Override
	public void saveFile(Presentation p, String fn) throws IOException {
		StringWriter out = new StringWriter();
		JAXB.marshal(p, out);
		String xmlString = out.toString();
		FileWriter writer = null;
		try {
			writer = new FileWriter(fn);
			writer.write(xmlString);
		}
		finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
