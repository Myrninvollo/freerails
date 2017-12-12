
/*
* RailModel.java
*
* Created on 10 July 2001, 13:24
*/
package jfreerails.common.trackmodel;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class RailModel extends java.lang.Object {

    private Object nodeA;

    private int length;

    private int gradient = 0;

    private boolean doubleTrack = false;

    private Object[] trainList;

    private Object nodeB;
    
    public boolean getDoubleTrack() {
        return doubleTrack;
    }
    
    public Object[] getTrainList() {
        return trainList;
    }
    
    public void removeTrain( Object train ) {
        
    }
    
    public Object[] getNodeList() {
        return new Object[] {
            nodeA, nodeB
        };
    }
    
    public int getGradient() {
        return gradient;
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
    
    public void removeRail() {
        
    }
    
    public void addTrain( Object train ) {
        
    }
    
    /** Creates new RailModel */
    
    public RailModel( Object nodeA, Object nodeB, int length, int direction ) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }
    
    public int getLength() {
        return length;
    }
    
    public void setDoubleTrack( boolean doubleTrack ) {
        this.doubleTrack = doubleTrack;
    }
}
