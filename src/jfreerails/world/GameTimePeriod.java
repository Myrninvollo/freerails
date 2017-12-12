package jfreerails.world;

import jfreerails.world.misc.GameTime;


final public class GameTimePeriod {

 
    private final GameTime start;


    private final GameTime end;

    public GameTime getStart() {
        return start;
    }

    public GameTime getEnd() {
        return end;
    }
    
    public GameTimePeriod(GameTime from, GameTime to){
    	
    	start=from;
    	end=to;
    }
}





