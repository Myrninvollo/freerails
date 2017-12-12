
/*
* RailModel.java
*
* Created on 10 July 2001, 13:24
*/
package jfreerails.common.trackmodel;

/** This class represents the track that connects two squares/nodes.
*
*
*
*
* @author Luke Lindsay
* @version 0.1
*/


public class RailModel extends java.lang.Object {

    private Object nodeB;

    private Object nodeA;

    private int length;

    private int gradient = 0;

    private boolean doubleTrack = false;

    private Object[] trainList;
    
    public void setDoubleTrack( boolean doubleTrack ) {
        this.doubleTrack = doubleTrack;
    }
    
    public void removeTrain( Object train ) {
        
    }
    
    public int getLength() {
        return length;
    }
    
    public Object getOtherNode( Object thisNode ) {
        if( thisNode == nodeA ) {
            return nodeB;
        }
        else {
            if( thisNode == nodeA ) {
                return nodeB;
            }
            else {
                System.out.println( "Error in jfreerails.common.trackmodel.RailModel.getOtherNode" + "- the node passed as an argument is not connected to this rail" );
            }
        }
        return null;
    }
    
    public Object[] getTrainList() {
        return trainList;
    }
    
    public void addTrain( Object train ) {
        
    }
    
    /** Creates new RailModel
    * @param nodeA The first of the track-nodes this rail connects.
    * @param nodeB The second of the ...
    * @param length The length of the track piece.
    * @param direction the direction of the rail, e.g. N.E.
    */
    
    public RailModel( Object nodeA, Object nodeB, int length, int direction ) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }
    
    public int getGradient() {
        return gradient;
    }
    
    public boolean getDoubleTrack() {
        return doubleTrack;
    }
    
    public void removeRail() {
        
    }
    
    public Object[] getNodeList() {
        return new Object[] {
            nodeA, nodeB
        };
    }
}
