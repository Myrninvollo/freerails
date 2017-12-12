

package jfreerails.world;
import java.util.Vector;

import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.move.Move;
import jfreerails.move.receiver.MoveReceiver;
import jfreerails.move.status.MoveStatus;
import jfreerails.world.std_track.TrackAndTerrainTileMap;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ProxyWorldVisibleToPlayer implements MoveReceiver, World {


	public Vector moveReceiver = new Vector();


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

	

	public MoveStatus processMove(Move Move) {
		return null;
	}
	

	public int getPopulation() {
		return 0;
	}
	
	public void changePopulation(int oldPop, int newPop) {
	}

	private World world;

	public ProxyWorldVisibleToPlayer(World w) {
		this.world = w;
	}
	public TrackAndTerrainTileMap getMap() {
		return world.getMap();
	}
	public TerrainTileTypesList getTerrainTileTypesList() {
		return world.getTerrainTileTypesList();
	}
	public TrackRuleList getTrackRuleList() {
		return world.getTrackRuleList();
	}

}