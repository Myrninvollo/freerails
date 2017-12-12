/*
 * TrackTileMapImpl.java
 *
 * Created on 23 January 2002, 22:25
 */

package jfreerails.world.std_track;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import jfreerails.list.TrackRuleList;

/**
 *
 * @author  lindsal
 * @version
 */
final public class TrackTileMapImpl implements jfreerails.world.std_track.TrackTileMap {
    
    private final TrackPiece[][] map;
    
    private final Rectangle mapSize;
        
    
    /** Creates new TrackTileMapImpl */
    public TrackTileMapImpl(Dimension size) {
        mapSize=new Rectangle(size);
        map=new TrackPiece[mapSize.width][mapSize.height];
        TrackPiece nullTrackPiece=NullTrackPiece.getInstance();
        for (int x=0;x<mapSize.width;x++){
            for (int y=0;y<mapSize.height;y++){
                map[x][y]=nullTrackPiece;
            }
        }
        
    }
    
    public boolean boundsContain(Point location) {
       return mapSize.contains(location);
    }
    
    public Dimension getMapSize() {
        return new Dimension(mapSize.width, mapSize.height);
    }
    
    public int getTrackGraphicNumber(Point point) {
        return  map[point.x][point.y].getTrackGraphicNumber();
    }
    
    public TrackPiece getTrackPiece(Point point) {
        return  map[point.x][point.y];
    }
    
    public int getTrackTypeNumber(Point point) {
        return  map[point.x][point.y].getTrackRule().getRuleNumber();
    }
    
    public boolean trackIsAllowed(Point location, int type) {
        return  true;   //TODO Change this later  LL
    }
    
    public void setTrackPiece(Point point, TrackPiece trackPiece) {
        //The error checks are performed elsewhere.
        map[point.x][point.y]=trackPiece;
    }
    
    public int getRGB(Point point){
    	return map[point.x][point.y].getRGB();
    	
    }
     public TrackRuleList getTrackRuleList(){
     	return null;
     }
    
}
