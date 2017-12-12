
/*
* DOMLoader.java
*
* Created on 22 May 2001, 15:48
*/
package jfreerails.lib;

/**
*
* @author  lindsal8
* @version 
*/
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.w3c.dom.*;
import org.w3c.dom.DOMException;


public class DOMLoader extends java.lang.Object {

    static Document document;
    
    /** Creates new load_dom */
    
    public DOMLoader() {
        
    }
    
    public static Document get_dom( URL xml_url ) {
        DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
        
        //factory.setValidating(true);   
        
        //factory.setNamespaceAware(true);
        System.out.println( "\nLoading XML " + xml_url );
        try {
            DocumentBuilder  builder = factory.newDocumentBuilder();
            File  file = new File( xml_url.getFile() );
            document = builder.parse( file );
        }
        catch( SAXException sxe ) {
            
            // Error generated during parsing)
            Exception  x = sxe;
            if( sxe.getException() != null ) {
                x = sxe.getException();
            }
            x.printStackTrace();
        }
        catch( ParserConfigurationException pce ) {
            
            // Parser with specified options can't be built
            pce.printStackTrace();
        }
        catch( IOException ioe ) {
            
            // I/O error
            ioe.printStackTrace();
        }
        return document;
    }
}
