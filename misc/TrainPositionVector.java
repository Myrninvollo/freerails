/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.misc;

import java.util.*;

import java.awt.Point;
import java.lang.String;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainPositionVector implements TrainPosition {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int startTime;
/**
 * Represents ...

 */

    private int endTime;
/**
 * Represents ...

 */

    private int acceleration;
/**
 * Represents ...

 */

    private int initialVelocity;
/**
 * Represents ...

 */

    private int initialPosition;

  ///////////////////////////////////////
  //access methods for attributes

    public int getStartTime() {
        return startTime;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public int getEndTime() {
        return endTime;
    }
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    public int getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }
    public int getInitialVelocity() {
        return initialVelocity;
    }
    public void setInitialVelocity(int initialVelocity) {
        this.initialVelocity = initialVelocity;
    }
    public int getInitialPosition() {
        return initialPosition;
    }
    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }

  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @return A double with ...
 */

    public double getDirection() {
        return 0.0;
    }
/**
 * Does ...
 * 
 * @return A Point with ...
 */

    public Point getPosition() {
        return null;
    }
/**
 * Does ...
 * 
 * @return A int with ...
 */

    public int getSpeed() {
        return 0;
    }
/**
 * Does ...
 * 
 * @return A String with ...
 */

    public String getDescription() {
        return null;
    }



}





