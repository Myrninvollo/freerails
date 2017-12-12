package jfreerails.controller.pathfinder;

/** This interface lets the caller explorer a graph while hiding the
 * way the graph is stored.  Vertices are packed into single ints
 * to avoid the cost of object creation and garbage collection.
 *
 * 24-Nov-2002
 * @author Luke Lindsay
 */
public interface Explorer {
	
	void setPosition(int i);
	
	int getPosition();
	
	void nextBranch();
	
	int getBranchPosition();
	
	int getBranchLength();
	
	boolean hasNextBranch();

	void moveForward();

	/***********************************/
	//scott bennett 15/03/03
	boolean isAtStation();
	/***********************************/
}
