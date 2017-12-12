package jfreerails.misc;

/**
 * 24-Nov-2002
 * @author Luke Lindsay
 *
 */
public interface Explorer {
	
	void setPosition(int i);
	
	int getPosition();
	
	void nextBranch();
	
	int getBranchPosition();
	
	int getBranchLength();
	
	boolean hasNextBranch();

	void moveForward();
}
