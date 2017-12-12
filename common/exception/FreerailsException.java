
/*
* FreerailsException.java
*
* Created on 11 July 2001, 11:58
*/
package jfreerails.common.exception;

/** Use this Exception to signify that the problem was
* specific to freerails.  E.g. a rgb value not being
* mapped to a terrain-type.
* @author Luke Lindsay
* @version 1.0
*/


public class FreerailsException extends java.lang.Exception {
    
    /**
    * Constructs an <code>FreerailsException</code> with the specified detail message.
    * @param msg the detail message.
    */
    
    public FreerailsException( String msg ) {
        super( msg );
    }
    
    /**
    * Creates new <code>FreerailsException</code> without detail message.
    */
    
    public FreerailsException() {
        
    }
}
