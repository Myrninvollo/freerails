/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package experimental;
/**
 * @version 	1.0
 * @author
 */
public class MoveHandler {
	
	final Board b;
	
	public MoveHandler(Board _b){
		b=_b;	
	}
	
	public void recieveMove(BoardMove move){
		move.doMove(b);			
	}
}
