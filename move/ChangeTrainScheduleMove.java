/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.Schedule;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeTrainScheduleMove extends AbstractTrainMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public Schedule oldSchedule;
/**
 * Represents ...

 */

    public Schedule newSchedule;

  ///////////////////////////////////////
  //access methods for attributes

    public Schedule getOldSchedule() {
        return oldSchedule;
    }
    public void setOldSchedule(Schedule oldSchedule) {
        this.oldSchedule = oldSchedule;
    }
    public Schedule getNewSchedule() {
        return newSchedule;
    }
    public void setNewSchedule(Schedule newSchedule) {
        this.newSchedule = newSchedule;
    }


}





