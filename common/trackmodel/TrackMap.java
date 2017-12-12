
/*
* TrackMap.java
*
* Created on 10 July 2001, 15:07
*/
package jfreerails.common.trackmodel;
import jfreerails.common.trackmodel.TrackNode;
import jfreerails.common.OneTileMoveVector;
import jfreerails.common.IntPoint;
import jfreerails.common.trackmodel.TrackRule;
import jfreerails.common.exception.FreerailsException;


/**
*
* @author  Luke Lindsay
* @version 
*/

/*This class encapsulates a map 2D map of track nodes.  It includes methods to add nodes to the map, 
change their properties - i.e. build stations and bridges, toggle double track etc - and 
add/remove rails between nodes.
*/

public class TrackMap extends java.lang.Object {

    private TrackNode[][] trackNodeMap;
    
    
    
    public int upgradeBuilding(IntPoint point, int buildingType ) {
        
        //TODO add code
        return 0;
    }
    
    /** Creates new TrackMap */
    
    public TrackMap( int x, int y ) {
        trackNodeMap = new TrackNode[ x ][ y ];
    }
    
    public int buildBuilding( IntPoint point, int buildingType ) {
        
        //TODO add code
        return 0;
    }
    
    public int toggleDoubleTrack(IntPoint point) {
        
        //TODO add code
        return 0;
    }
    
    public int removeBuilding( IntPoint point ) {
        
        //TODO add code
        return 0;
    }
    
    public TrackNode getTrackNode( IntPoint point ) {
        return trackNodeMap[ point.getX() ][point.getY() ];
    }
    
    public int removeTrack( IntPoint point, OneTileMoveVector direction ) {
        
        //TODO add code
        return 0;
    }
    
    public int getTrackGraphicNumber( IntPoint point) {
        TrackNode  trackNode = getTrackNode(point );
        if( trackNode == null ) {
            return 0;
        }
        else {
            return trackNode.getTrackGraphicNumber();
        }
    }
    
    public void addTrackNode(IntPoint point, TrackNode trackNode ) throws FreerailsException {
        if (trackNodeMap[ point.getX() ][ point.getY() ] == null){
        trackNodeMap[  point.getX() ][  point.getY() ] = trackNode;
    }
        else{
            throw new FreerailsException("Tried to add a track node at"+point.getX()+", "+point.getY()+" but there is already one there");
        }
    }
    public int getHeight(){
        return trackNodeMap[0].length;
    }
    public int getWidth(){
        return trackNodeMap.length;
    }
}
