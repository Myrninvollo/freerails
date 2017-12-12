/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package experimental;
/**
 * @version 	1.0
 * @author
 */
public class Board {
	private Piece[][] board=new Piece[8][8];
	
	public void setPiece(int x, int y, Piece p){
		board[x][y]=p;
	}
	public Piece getPiece(int x, int y){
		return board[x][y];	
	}
	public int getWidth(){
		return board.length;
	}
	public int getHeight(){
		return board[0].length;
	}

}
