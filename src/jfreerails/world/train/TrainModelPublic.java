
package jfreerails.world.train;

import jfreerails.lib.FreerailsSerializable;

public class TrainModelPublic implements FreerailsSerializable{
    
    Snake trainposition = new SnakeImpl();
    
    int wagons = 3;
    
    public TrainModelPublic(){
        
    }
    
    public int getWagons(){
        return wagons;
    }
    
    public void setWagons(int i){
        wagons=i;
    }
    
    public Snake getPosition(){
        return  trainposition;
    }
}
