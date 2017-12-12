/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.type;

import java.util.*;

import jfreerails.element.TrainModelPublic;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainClass {

   ///////////////////////////////////////
   // associations

    public TrainModelPublic trainModelPublic;


   ///////////////////////////////////////
   // access methods for associations


    public TrainModelPublic getTrainModelPublic() {
        return trainModelPublic;
    }
    public void setTrainModelPublic(TrainModelPublic trainModelPublic) {
        if (this.trainModelPublic != trainModelPublic) {
            this.trainModelPublic = trainModelPublic;
            if (trainModelPublic != null)
                trainModelPublic.setTrainClass(this);  
        }      
    } 



}





