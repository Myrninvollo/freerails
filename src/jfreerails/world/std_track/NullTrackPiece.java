/*
 * NullTrackPiece.java
 *
 * Created on 23 January 2002, 21:31
 */

package jfreerails.world.std_track;

import java.io.ObjectStreamException;

import jfreerails.world.type.TrackRule;
import jfreerails.world.flat.TrackConfiguration;

/**
 *
 * @author  lindsal
 * @version
 */
final public class NullTrackPiece implements jfreerails.world.std_track.TrackPiece {
    
    private static final TrackPiece nullTrackPiece=new NullTrackPiece();  
   
    /** Creates new NullTrackPiece */
    private NullTrackPiece() {
    }
    
    public static TrackPiece getInstance(){
        return nullTrackPiece;
    }
    
    
    public int getRGB() {
        return 0;
    }
    
    
    public int getTrackGraphicNumber() {
        return 0;
    }
    
    public TrackRule getTrackRule() {
        return NullTrackType.getInstance();
    }
    
    public TrackConfiguration getTrackConfiguration() {
        return TrackConfiguration.getFlatInstance(0);
    }
    
    private Object readResolve() throws ObjectStreamException {
    	return nullTrackPiece;	
    }
    
}
