/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package experimental;
/**
 * @version 	1.0
 * @author
 */
public class BoardMove {
	public final int x,y;
	public final Piece before, after;
	
	public BoardMove(int _x, int _y, Piece b, Piece a){
		x=_x;
		y=_y;
		after=a;
		before=b;
	}
	
	public boolean doMove(Board b){
		if(b.getPiece(x,y)==before){
			b.setPiece(x,y,after);
			return true;
		}else{
			return false;
		}	
	}

}
