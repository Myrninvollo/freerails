
/*
* Interface.java
*
* Created on 08 August 2001, 20:25
*/
package jfreerails.client.event;

/**
*
* @author  Luke Lindsay
* @version 
*/


public interface CursorEventListener extends java.util.EventListener {
    
    public void cursorOneTileMove( CursorEvent ce );
    
    public void cursorJumped( CursorEvent ce );
    
    public void cursorKeyPressed( CursorEvent ce );
}
