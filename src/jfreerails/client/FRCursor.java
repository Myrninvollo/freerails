package jfreerails.client;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import jfreerails.client.event.CursorEventListener;

public interface FRCursor extends KeyListener {
	void blinkCursor();
	void keyTyped(KeyEvent keyEvent);

	void addCursorEventListener(CursorEventListener l);

	void keyReleased(KeyEvent keyEvent);

	void removeCursorEventListener(CursorEventListener l);

	void TryMoveCursor(Point tryThisPoint);

	void keyPressed(KeyEvent keyEvent);

}
