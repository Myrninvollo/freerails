package jfreerails.element.observer;

import java.util.*;

import jfreerails.element.TrainModelPublic;

/**
 * Defining operations expected of ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public interface TrainListener {

  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param train ...
 */

    public void trainDidSomething(TrainModelPublic train);

}

