
package jfreerails.world.train;

import java.util.ArrayList;

import jfreerails.world.misc.FreerailsSerializable;
public class TrainList implements FreerailsSerializable {
    
    private ArrayList trains=new ArrayList();

    public TrainModel trainModelPublic;
    
    public TrainList(){
        
    }
    
    public void addTrain(TrainModel train){
        trains.add(train);
    }
    
    public TrainModel getTrain(int i){
        return (TrainModel)trains.get(i);
    }
    
    public int size(){
        return trains.size();
    }       
}





