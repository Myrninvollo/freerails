
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


public class DOMLoader extends java.lang.Object {

    static org.w3c.dom.Document document;
    
    /** Creates new load_dom */
    
    public DOMLoader() {
        
    }
    
    public static org.w3c.dom.Document get_dom( java.net.URL xml_url ) {
        javax.xml.parsers.DocumentBuilderFactory  factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
           
        System.out.println( "\nLoading XML " + xml_url );
        try {
            javax.xml.parsers.DocumentBuilder  builder = factory.newDocumentBuilder();           
            document = builder.parse( xml_url.toExternalForm() );
        }
        catch( org.xml.sax.SAXException sxe ) {
            
            // Error generated during parsing)
            Exception  x = sxe;
            if( sxe.getException() != null ) {
                x = sxe.getException();
            }
            x.printStackTrace();
        }
        catch( javax.xml.parsers.ParserConfigurationException pce ) {
            
            // Parser with specified options can't be built
            pce.printStackTrace();
        }
        catch( java.io.IOException ioe ) {
            
            // I/O error
            ioe.printStackTrace();
        }
        return document;
    }
}
