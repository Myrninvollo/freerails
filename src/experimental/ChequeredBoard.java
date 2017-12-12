/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package experimental;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * @version 	1.0
 * @author
 */

public class ChequeredBoard extends JPanel {

	final Board board;
	final MoveHandler moveHandler;

	public ChequeredBoard(Board b, MoveHandler mh) {
		moveHandler = mh;
		board = b;
		this.addMouseListener(new MyMouseAdapter());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {

				if ((x + y) % 2 == 0) {
					g2.setColor(Color.WHITE);
				} else {
					g2.setColor(Color.BLACK);
				}
				g2.fillRect(x * 40, y * 40, 40, 40);
				Piece p = board.getPiece(x, y);
				if (p != null) {
					g2.setColor(p.colour);
				}
				g2.fillRect(x * 40 + 5, y * 40 + 5, 30, 30);

			}
		}
	}
	
	private class MyMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int x = e.getX() / 40;
			int y = e.getY() / 40;
			if (x >= 0 && y >= 0 && x < board.getWidth() && y < board.getHeight()) {
				Piece p = board.getPiece(x, y);
				if (null == p) {

					moveHandler.recieveMove(new BoardMove(x, y, p, new Piece(Color.BLUE)));
				} else {
					moveHandler.recieveMove(new BoardMove(x, y, p, null));
				}
			}
			repaint();
		}
	}

}