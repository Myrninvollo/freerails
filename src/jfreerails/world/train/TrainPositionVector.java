
package jfreerails.world.train;


import java.awt.Point;


/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainPositionVector implements TrainPosition {



    private int startTime;

    private int endTime;


    private int acceleration;

    private int initialVelocity;


    private int initialPosition;


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



    public double getDirection() {
        return 0.0;
    }


    public Point getPosition() {
        return null;
    }

    public int getSpeed() {
        return 0;
    }


    public String getDescription() {
        return null;
    }



}





