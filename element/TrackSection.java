/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.element;

import java.util.Vector;

import jfreerails.element.container.TrainContainer;
import jfreerails.misc.OneTileMoveVector;
import jfreerails.misc.SimpleTrainPosition;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class TrackSection implements TrainContainer {

	///////////////////////////////////////
	//attributes

	private int length = 100;

	private int gradient = 0;

	private boolean doubleTrack = false;

	private Object[] trainList;

	private TrackNode nodeB;

	private TrackNode nodeA;
	
	private OneTileMoveVector directionAtNodeA;
	
	private OneTileMoveVector directionAtNodeB;

	///////////////////////////////////////
	// associations

	public Vector trackNode = new Vector();
	public Vector signalBlock = new Vector();
	public Signal signal;

	///////////////////////////////////////
	//access methods for attributes

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getGradient() {
		return gradient;
	}
	public void setGradient(int gradient) {
		this.gradient = gradient;
	}

	///////////////////////////////////////
	// access methods for associations

	public Vector getTrackNode() {
		return trackNode;
	}

	public Vector getSignalBlock() {
		return signalBlock;
	}
	public void addSignalBlock(SignalBlock signalBlock) {
		if (!this.signalBlock.contains(signalBlock)) {
			this.signalBlock.addElement(signalBlock);
			signalBlock.addTrackSection(this);
		}
	}
	public void removeSignalBlock(SignalBlock signalBlock) {
		if (this.signalBlock.removeElement(signalBlock)) {
			signalBlock.removeTrackSection(this);
		}
	}

	public Signal getSignal() {
		return signal;
	}
	public void setSignal(Signal signal) {
		if (this.signal != signal) {
			this.signal = signal;
			if (signal != null)
				signal.setTrackSection(this);
		}
	}

	public Object[] getNodeList() {
		return new Object[] { nodeA, nodeB };
	}

	public void removeRail() {

	}

	public void addTrain(Object train) {

	}

	public void removeTrain(Object train) {

	}

	public boolean getDoubleTrack() {
		return doubleTrack;
	}

	public Object[] getTrainList() {
		return trainList;
	}

	public void setDoubleTrack(boolean doubleTrack) {
		this.doubleTrack = doubleTrack;
	}

	public Object getOtherNode(Object thisNode) {
		if (thisNode == nodeA) {
			return nodeB;
		} else {
			if (thisNode == nodeA) {
				return nodeB;
			} else {
				System.out.println(
					"Error in jfreerails.common.trackmodel.RailModel.getOtherNode"
						+ "- the node passed as an argument is not connected to this rail");
			}
		}
		return null;
	}
	///////////////////////////////////////
	// operations

	/**
	 * Does ...
	 * 
	 * @param trackDistanceTravelled ...
	 * @return A SimpleTrainPosition with ...
	 */

	public SimpleTrainPosition getTrainPosition(int trackDistanceTravelled) {
		return null;
	}
	public TrackSection(TrackNode a, OneTileMoveVector va, TrackNode b, OneTileMoveVector vb){
		nodeA=a;
		nodeB=b;
		directionAtNodeA=va;
		directionAtNodeB=vb;
		nodeA.addTrackSection(directionAtNodeA,this);
		nodeB.addTrackSection(directionAtNodeB,this);
	}
	public void destroy(){
		nodeA.removeTrackSection(directionAtNodeA);
		nodeB.removeTrackSection(directionAtNodeB);		
	}

}