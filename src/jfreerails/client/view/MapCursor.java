package jfreerails.client.view;

import java.awt.Point;

import jfreerails.client.view.CursorEventListener;

public interface MapCursor {
    /**
     * calling this toggles the cursor colour
     */
    public void blinkCursor();

    /** Moves the cursor provided the destination is a legal position.
     * @param tryThisPoint The cursor's destination.
     */
    public void TryMoveCursor(Point tryThisPoint);

    /** Adds a listener.  Listeners could include: the trackbuild system, the
     * view the cursor moves across, etc.
     * @param l The listener.
     */
    public void addCursorEventListener(CursorEventListener l);

    /** Removes a listener.
     * @param l The listener.
     */
    public void removeCursorEventListener(CursorEventListener l);
}

