package jfreerails.world.std_track;

import jfreerails.type.TrackRule;
import jfreerails.world.flat.*;

final public class TrackPieceImpl implements TrackPiece {
    private final jfreerails.world.flat.TrackConfiguration configuration;
    
    private final TrackRule trackType;
    
    public TrackPieceImpl(jfreerails.world.flat.TrackConfiguration c,TrackRule type){
        configuration=c;
        trackType=type;
        
    }
    public int getRGB(){
        return 0;
    }
    
    public int getTrackGraphicNumber() {
        return configuration.getTrackGraphicsNumber();
    }
    
    public int getTrackTypeNumber() {
        return trackType.getRuleNumber();
    }
    
    public TrackRule getTrackRule() {
        return trackType;
    }
    
    public TrackConfiguration getTrackConfiguration() {
        return configuration;
    }
    
}
