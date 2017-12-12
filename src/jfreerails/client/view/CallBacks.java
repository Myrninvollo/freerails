/*
 * Created on 25-Aug-2003
 */
package jfreerails.client.view;

import jfreerails.move.Move;

/**
 * @author Luke Lindsay
 *
 */
public interface CallBacks {
	
	public static final CallBacks  NULL_INSTANCE = new CallBacks(){
		public void closeDialogue() {}
		public void moveCursor(int x, int y){}	
		public void processMove(Move m) {}			
	};
	
	void closeDialogue();
	
	void moveCursor(int x, int y);
	
	void processMove(Move m);
		
}
