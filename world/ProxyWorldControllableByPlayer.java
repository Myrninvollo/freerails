/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */

package jfreerails.world;
import jfreerails.list.TerrainTileTypesList;
import jfreerails.list.TrackRuleList;
import jfreerails.map.FreerailsMap;



/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ProxyWorldControllableByPlayer implements World {
	private World world;
	 
	 public ProxyWorldControllableByPlayer(World w){
	 	this.world=w;
	 }
	 public FreerailsMap getMap(){
		return world.getMap();
	}
	public TerrainTileTypesList getTerrainTileTypesList(){
		return world.getTerrainTileTypesList();
	}
	public TrackRuleList getTrackRuleList(){
		return world.getTrackRuleList();
	}
	

}





