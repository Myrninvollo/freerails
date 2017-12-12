
package jfreerails.world.track;

import java.util.Iterator;
import java.util.Vector;


import jfreerails.world.misc.Player;


public class TrackList {


    public Vector trackNodeVector = new Vector();
   
    public Vector getTrackNode() {
        return trackNodeVector;
    }
//    public void addTrackNode(TrackNode trackNode) {
//        if (! this.trackNodeVector.contains(trackNode)) {     
//            this.trackNodeVector.addElement(trackNode);  
//            trackNode.setTrackList(this);
//        }
//    }
//    public void removeTrackNode(TrackNode trackNode) {
//        if (this.trackNodeVector.removeElement(trackNode)) {      
//            trackNode.setTrackList(null);
//        }
//    }

//    public RRCompany getRRCompany() {
//        return rRCompany;
//    }
//    public void setRRCompany(RRCompany rRCompany) {
//        if (this.rRCompany != rRCompany) {
//            this.rRCompany = rRCompany;
//            if (rRCompany != null)
//                rRCompany.setTrackList(this);  
//        }      
//    } 

//    public GameElementsList getGameElementsList() {
//        return gameElementsList;
//    }
//    public void setGameElementsList(GameElementsList gameElementsList) {
//        if (this.gameElementsList != gameElementsList) {
//            this.gameElementsList = gameElementsList;
//            if (gameElementsList != null)
//                gameElementsList.setTrackList(this);  
//        }      
//    } 
//



    public Iterator getTrackNodesIterator() {
        return null;
    }

    public Iterator getTrackSectionsIterator() {
        return null;
    }

    public Player getOwner() {
        return null;
    }
    



}





