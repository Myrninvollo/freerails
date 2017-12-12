package jfreerails.controller;

import jfreerails.world.common.FreerailsSerializable;


/**
 * This interface should be implemented by objects wishing to receive data from
 * a ConnectionToServer object.
 */
public interface EventReceiver {
    public void receiveEvent(FreerailsSerializable e);
}