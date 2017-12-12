package jfreerails.world.std_track;

import jfreerails.type.TrackRule;

final public class TrackPieceImpl implements TrackPiece {
    private final jfreerails.world.std_track.TrackConfiguration configuration;
    
    private final TrackRule trackType;
    
    public TrackPieceImpl(jfreerails.world.std_track.TrackConfiguration c,TrackRule type){
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
