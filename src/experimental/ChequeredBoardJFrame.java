/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package experimental;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * @version 	1.0
 */
public class ChequeredBoardJFrame extends JFrame {

	Board b=new Board();
	MoveHandler moveHandler=new MoveHandler(b);

	public ChequeredBoardJFrame() {

		b.setPiece(3,3,new Piece(Color.RED));
		this.getContentPane().add(new ChequeredBoard(b, moveHandler));
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
		this.pack();
		this.setSize(400, 400);
	}

	public static void main(String args[]) {
		ChequeredBoardJFrame chequeredBoardJFrame = new ChequeredBoardJFrame();
		chequeredBoardJFrame.show();

	}

}