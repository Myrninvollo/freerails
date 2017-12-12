
/*
* FreerailsException.java
*
* Created on 11 July 2001, 11:58
*/
package jfreerails.common.exception;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class FreerailsException extends java.lang.Exception {
    
    /**
    * Creates new <code>FreerailsException</code> without detail message.
    */
    
    public FreerailsException() {
        
    }
    
    /**
    * Constructs an <code>FreerailsException</code> with the specified detail message.
    * @param msg the detail message.
    */
    
    public FreerailsException( String msg ) {
        super( msg );
    }
}
