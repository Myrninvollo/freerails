package jfreerails.move;
public class IllegalMoveFreerailsException extends Exception {

    
    /**
    * Constructs an <code>FreerailsException</code> with the specified detail message.
    * @param msg the detail message.
    */
    
    public IllegalMoveFreerailsException( String msg ) {
        super( msg );
    }
    
    /**
    * Creates new <code>FreerailsException</code> without detail message.
    */
    
    public IllegalMoveFreerailsException() {
        
    }
}

