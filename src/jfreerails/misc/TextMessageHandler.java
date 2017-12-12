
/*
* TextMessageHandler.java
*
* Created on 25 July 2001, 04:30
*/
package jfreerails.misc;



/**
*
* @author  Luke Lindsay
*/


public class TextMessageHandler {

    private static TextMessenger messageSender = null;
    
    public static synchronized void setMessengerBoy( TextMessenger mBoy ) {
        messageSender = mBoy;
    }
    
    public static synchronized void sendMessage( String message ) {
        if( messageSender == null ) {
            System.out.println( message );
        }
        else {
            messageSender.displayMessage( message );
        }
    }
}
