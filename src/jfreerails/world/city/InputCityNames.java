/**
 * @author Scott Bennett
 * Date: 31st March 2003
 *
 * Class that calls the object to input the City names and co-ords from an xml file.
 */

package jfreerails.world.city;

import java.io.*;
import java.net.URL;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;

import jfreerails.world.top.World;

public class InputCityNames {

	private World world;
	
	public InputCityNames(World w, URL filename) throws SAXException {

		world = w;
		InputSource is = new InputSource(filename.toString());
		
		DefaultHandler handler = new CitySAXParser(world);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(is, handler);
			
		} catch (IOException e) {
			System.out.println("There was a IOException, in jfreerails.world.city.InputCityNames");
		} catch (ParserConfigurationException pce) {
			System.out.println("There was a ParserConfigurationException, in jfreerails.world.city.InputCityNames");
		}

		System.out.println("\nLoading XML " + filename);
	}
	
}
