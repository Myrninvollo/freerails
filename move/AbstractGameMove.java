/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.massnoun.GameTime;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public abstract class AbstractGameMove implements Move{

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int serial;
/**
 * Represents ...

 */

    private GameTime timeStamp;

  ///////////////////////////////////////
  //access methods for attributes

    public int getSerial() {
        return serial;
    }
    public void setSerial(int serial) {
        this.serial = serial;
    }
    public GameTime getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(GameTime timeStamp) {
        this.timeStamp = timeStamp;
    }


}





