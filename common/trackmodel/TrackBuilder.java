/*
 * TrackBuilder.java
 *
 * Created on 20 July 2001, 00:52
 */

package jfreerails.common.trackmodel;
import jfreerails.common.IntPoint;
import jfreerails.common.trackmodel.TrackRule;
import jfreerails.common.trackmodel.TrackNode;
import jfreerails.common.trackmodel.TrackMap;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.OneTileMoveVector;
/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class TrackBuilder extends java.lang.Object {

    private TrackMap trackMap;
    private TrackRule trackRule;
    
    /** Creates new TrackBuilder */
    public TrackBuilder(TrackMap trackMap, TrackRule trackRule) {
        this.trackMap=trackMap;
        this.trackRule=trackRule;
    }
    public void setTrackRule(TrackRule trackRule){
        this.trackRule=trackRule;
    }
    public TrackRule getTrackRule(){
        return this.trackRule;
    }

    public boolean buildTrack(IntPoint from,OneTileMoveVector trackVector) throws FreerailsException {
        TrackNode trackNodeA, trackNodeB;
        IntPoint pointA=new IntPoint( from.getX(), from.getY());
        IntPoint pointB=new IntPoint(from.getX()+trackVector.getX(),from.getY()+trackVector.getY());
        trackNodeA=trackMap.getTrackNode(pointA);
        trackNodeB=trackMap.getTrackNode(pointB);
        
        /* If the either of the nodes do not already exist, we need to create them. */
        if (null==trackNodeA){
           trackNodeA=new TrackNode(pointA, trackRule );
           //TODO add code to check that no track has been built yet.  I.e. must connect to existing track.
        }
         if (null==trackNodeB){
           trackNodeB=new TrackNode(pointB, trackRule );
        }
        
        /* We need to check that building this piece of track will result in a legal track configuration. 
         and that the track we are buliding does not cross any other track*/
       if ((trackNodeA.testAddRail(trackVector, trackRule)==true)
        &&(trackNodeB.testAddRail(trackVector.getOpposite(), trackRule)==true)
        &&(noDiagonalTrackConflicts(pointA, trackVector)==true)
        &&(noDiagonalTrackConflicts(pointB, trackVector.getOpposite())==true)){
            trackNodeA.addRail(trackVector, trackRule);
            trackNodeB.addRail(trackVector.getOpposite(), trackRule);
            
            //If we have just created tracknodeA, we need to add it to the track map.
            if(null==trackMap.getTrackNode(pointA) ){
                trackMap.addTrackNode(pointA,trackNodeA);
            }
            //If we have just created tracknodeB, we need to add it to the track map.
            if(null==trackMap.getTrackNode(pointB)){
                trackMap.addTrackNode(pointB,trackNodeB);
            }
            return true;
       }
       else{
           return false;
       }
    }
        /*Check that if the track we are buliding is diagonal, it does not cross a perpendicular diagonal track. */ 
        private boolean noDiagonalTrackConflicts(IntPoint point, OneTileMoveVector tv){
             int trackTemplate=(1<<(  3*(1+tv.getY())  + (1+tv.getX())  )  );
             int trackTemplateAbove;
             int trackTemplateBelow;
             
             //Avoid array-out-of-bounds exceptions.
             if(point.getY() > 0){
             trackTemplateAbove=trackMap.getTrackGraphicNumber(new IntPoint(point.getX(),point.getY()-1));
             }else{
                 trackTemplateAbove=0;
             }
             if(  (point.getY()+1) < trackMap.getHeight()){
                trackTemplateBelow=trackMap.getTrackGraphicNumber(new IntPoint(point.getX(),point.getY()+1));
            }else{
                 trackTemplateBelow=0;
             }
             
             
             trackTemplateAbove=trackTemplateAbove>>6;
             trackTemplateBelow=trackTemplateBelow<<6;
             trackTemplate=trackTemplate&(trackTemplateAbove|trackTemplateBelow);
             if(trackTemplate!=0){
                 return false;  //There is a clash.
             }
             else{
                 return true;   //Things are ok.
             }
             
            
        }
    

    
public boolean testBuildTrack(IntPoint from, OneTileMoveVector vector, TrackRule trackRule) {
    return false;
}

}
