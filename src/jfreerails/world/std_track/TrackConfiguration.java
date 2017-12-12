package jfreerails.world.std_track;

import java.util.ArrayList;
import java.util.Iterator;

import jfreerails.misc.OneTileMoveVector;
import jfreerails.type.LegalTrackConfigurations;
import jfreerails.world.track.TrackSection;
final public class TrackConfiguration {    
    /**
     * @associates TrackConfiguration 
     */
    private static final ArrayList flatTrackConfigurations=new ArrayList(512);
    
     static {
        for(int i=0;i<512;i++){
            flatTrackConfigurations.add(i,new TrackConfiguration(i));
        }                           
     }
    
    /** Returns a 9-bit value specifying  the track configuration, and
     * hence the appropriate icon, for the track at this node.  E.g.
     * the binary representation of a vertical straight would be:
     * 010
     * 010
     * 010 i.e. 010010010
     * @param railsList The rail list that is used ot generate the track
     * graphic number.
     * @return The track graphic number.
     */
    public static int getTrackGraphicNumber(TrackSection[][] railsList) {
        int trackGraphicNumber = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (1 == x && 1 == y) {
                    trackGraphicNumber = trackGraphicNumber | (1 << (3 * y + x));
                } else {
                    if (null != railsList[x][y]) {
                        trackGraphicNumber = trackGraphicNumber | (1 << (3 * y + x));
                    }
                }
            }
        }
        return trackGraphicNumber;
    }
    
    public boolean hasTrackSection(OneTileMoveVector rail) {
        return false;
    }
    
    private final int configuration;
    //private TrackSectionVector[] vectors;
    
    private TrackConfiguration(int i){
        configuration=i;
    }
       
    public int getTrackGraphicsNumber(){
        return configuration;
    }
    
    public Iterator getPossibleConfigurationsIterator(){
       return flatTrackConfigurations.iterator();          
    }
    
    public static TrackConfiguration getFlatInstance(int i){
        return (TrackConfiguration)(flatTrackConfigurations.get(i));
    }
    
    public static TrackConfiguration getFlatInstance(String template){
        int i=LegalTrackConfigurations.stringTemplate2Int(template);
        return (TrackConfiguration)(flatTrackConfigurations.get(i));
    }
    public static TrackConfiguration getInstanceWithVectorAdded(TrackConfiguration c, OneTileMoveVector v){
        int x=v.getX()+1;
        int y=v.getY()+1;
        int oldTemplate =c.getTrackGraphicsNumber();
        int newTemplate = oldTemplate | (1 << (3 * y + x));
        return getFlatInstance(newTemplate);
    }
    public static TrackConfiguration getInstanceWithVectorRemoved(TrackConfiguration c, OneTileMoveVector v){
        int x=v.getX()+1;
        int y=v.getY()+1;
        int oldTemplate =c.getTrackGraphicsNumber();
        int newTemplate = oldTemplate ^ (1 << (3 * y + x));
        return getFlatInstance(newTemplate);
    }
}
