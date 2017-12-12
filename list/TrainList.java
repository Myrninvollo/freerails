/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.list;

import java.util.*;

import jfreerails.element.RRCompany;
import jfreerails.element.TrainModelPublic;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainList {

   ///////////////////////////////////////
   // associations

    public RRCompany rRCompany;
    public TrainModelPublic trainModelPublic;
    public GameElementsList gameElementsList;


   ///////////////////////////////////////
   // access methods for associations


    public RRCompany getRRCompany() {
        return rRCompany;
    }
    public void setRRCompany(RRCompany rRCompany) {
        if (this.rRCompany != rRCompany) {
            this.rRCompany = rRCompany;
            if (rRCompany != null)
                rRCompany.setTrainList(this);  
        }      
    } 

    public TrainModelPublic getTrainModelPublic() {
        return trainModelPublic;
    }
    public void setTrainModelPublic(TrainModelPublic trainModelPublic) {
        if (this.trainModelPublic != trainModelPublic) {
            if (this.trainModelPublic != null) 
                this.trainModelPublic.removeTrainList(this);     
            this.trainModelPublic = trainModelPublic;
            if (trainModelPublic != null)
                trainModelPublic.addTrainList(this);  
        }
    } 

    public GameElementsList getGameElementsList() {
        return gameElementsList;
    }
    public void setGameElementsList(GameElementsList gameElementsList) {
        if (this.gameElementsList != gameElementsList) {
            this.gameElementsList = gameElementsList;
            if (gameElementsList != null)
                gameElementsList.setTrainList(this);  
        }      
    } 



}





