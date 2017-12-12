
package jfreerails.list;

import jfreerails.world.financial.RRCompany;
import jfreerails.world.train.TrainModelPublic;
/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrainList {

    public RRCompany rRCompany;
    public TrainModelPublic trainModelPublic;
    public GameElementsList gameElementsList;


    public RRCompany getRRCompany() {
        return rRCompany;
    }

    public TrainModelPublic getTrainModelPublic() {
        return trainModelPublic;
    }
    public void setTrainModelPublic(TrainModelPublic trainModelPublic) {
        if (this.trainModelPublic != trainModelPublic) {
            
            this.trainModelPublic = trainModelPublic;
            
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





