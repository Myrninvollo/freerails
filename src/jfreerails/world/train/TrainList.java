
package jfreerails.world.train;

import java.util.ArrayList;

import jfreerails.lib.FreerailsSerializable;
public class TrainList implements FreerailsSerializable {
    
    private ArrayList trains=new ArrayList();

    public TrainModelPublic trainModelPublic;
    
    public TrainList(){
        
    }
    
    public void addTrain(TrainModelPublic train){
        trains.add(train);
    }
    
    public TrainModelPublic getTrain(int i){
        return (TrainModelPublic)trains.get(i);
    }
    
    public int size(){
        return trains.size();
    }       
}





