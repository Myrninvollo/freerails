package jfreerails.world.std_track;
import jfreerails.type.TrackRule;
import jfreerails.world.tilemap.Tile;

public interface TrackPiece extends Tile {
    int getTrackGraphicNumber();    
    
    int getRGB();
    
    TrackRule getTrackRule();
    
    TrackConfiguration getTrackConfiguration();
    
}
