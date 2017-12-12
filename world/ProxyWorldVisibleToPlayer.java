/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package jfreerails.world;
import java.util.Vector;

import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;
import jfreerails.move.Move;
import jfreerails.move.receiver.MoveReceiver;
import jfreerails.move.status.MoveStatus;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ProxyWorldVisibleToPlayer implements MoveReceiver, World {

	///////////////////////////////////////
	// associations

	public Vector moveReceiver = new Vector();

	///////////////////////////////////////
	// access methods for associations

	public Vector getMoveReceiver() {
		return moveReceiver;
	}
	public void addMoveReceiver(MoveReceiver moveReceiver) {
		if (!this.moveReceiver.contains(moveReceiver)) {
			this.moveReceiver.addElement(moveReceiver);
		}
	}
	public void removeMoveReceiver(MoveReceiver moveReceiver) {
		this.moveReceiver.removeElement(moveReceiver);
	}

	///////////////////////////////////////
	// operations

	/**
	 * Does ...
	 * 
	 * @param Move ...
	 * @return A MoveStatus with ...
	 */

	public MoveStatus processMove(Move Move) {
		return null;
	}
	/**
	 * Does ...
	 * 
	 * @return A int with ...
	 */

	public int getPopulation() {
		return 0;
	}
	/**
	 * Does ...
	 * 
	 * @param oldPop ...
	 * @param newPop ...
	 */

	public void changePopulation(int oldPop, int newPop) {
	}

	private World world;

	public ProxyWorldVisibleToPlayer(World w) {
		this.world = w;
	}
	public FreerailsMap getMap() {
		return world.getMap();
	}
	public TerrainTileTypesList getTerrainTileTypesList() {
		return world.getTerrainTileTypesList();
	}
	public TrackRuleList getTrackRuleList() {
		return world.getTrackRuleList();
	}

}