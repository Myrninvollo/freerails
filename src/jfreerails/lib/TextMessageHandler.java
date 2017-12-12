
/*
* TextMessageHandler.java
*
* Created on 25 July 2001, 04:30
*/
package jfreerails.lib;
import jfreerails.client.TextMessenger;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class TextMessageHandler {

    private static TextMessenger messengerBoy = null;
    
    public static synchronized void setMessengerBoy( TextMessenger mBoy ) {
        messengerBoy = mBoy;
    }
    
    public static synchronized void sendMessage( String message ) {
        if( messengerBoy == null ) {
            System.out.println( message );
        }
        else {
            messengerBoy.displayMessage( message );
        }
    }
}
