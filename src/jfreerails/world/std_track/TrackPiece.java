package jfreerails.world.std_track;
import jfreerails.world.type.TrackRule;
import jfreerails.world.flat.TrackConfiguration;
import jfreerails.world.tilemap.Tile;

public interface TrackPiece extends Tile {
    int getTrackGraphicNumber();    
    
    int getRGB();
    
    TrackRule getTrackRule();
    
    TrackConfiguration getTrackConfiguration();
    
}
