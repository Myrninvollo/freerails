package jfreerails.misc;

import java.util.*;

import java.awt.Point;
import java.lang.String;

/**
 * Defining operations expected of ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public interface TrainPosition {

   ///////////////////////////////////////
  // associations



  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @return A double with ...
 */

    public double getDirection();
/**
 * Does ...
 * 
 * @return A Point with ...
 */

    public Point getPosition();
/**
 * Does ...
 * 
 * @return A int with ...
 */

    public int getSpeed();
/**
 * Does ...
 * 
 * @return A String with ...
 */

    public String getDescription();

}

