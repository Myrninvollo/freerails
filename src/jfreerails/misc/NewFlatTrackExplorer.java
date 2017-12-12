package jfreerails.misc;

import java.awt.Point;
import java.util.NoSuchElementException;

import jfreerails.world.misc.FreerailsSerializable;
import jfreerails.world.misc.OneTileMoveVector;
import jfreerails.world.track.PositionOnTrack;
import jfreerails.world.track.TrackConfiguration;
import jfreerails.world.track.TrackPiece;
import jfreerails.world.track.TrackTileMap;

/**
 * 24-Nov-2002
 * @author Luke Lindsay
 *
 */
public class NewFlatTrackExplorer implements Explorer, FreerailsSerializable {

	PositionOnTrack currentPosition = new PositionOnTrack(0, 0, OneTileMoveVector.NORTH);
	PositionOnTrack currentBranch = new PositionOnTrack(0, 0, OneTileMoveVector.NORTH);

	boolean beforeFirst = true;

	private final TrackTileMap map;

	public TrackTileMap getMap() {
		return map;
	}

	/**
	 * @see jfreerails.misc.Explorer#setPosition(int)
	 */
	public void setPosition(int i) {
		beforeFirst = true;
		currentPosition.setValuesFromInt(i);
	}

	/**
	 * @see jfreerails.misc.Explorer#getPosition()
	 */
	public int getPosition() {
		return this.currentPosition.toInt();
	}

	public void moveForward() {
		this.setPosition(this.getBranchPosition());
	}

	/**
	 * @see jfreerails.misc.Explorer#nextBranch()
	 */
	public void nextBranch() {

		if (!hasNextBranch()) {
			throw new NoSuchElementException();
		} else {
			OneTileMoveVector v = this.getFirstVectorToTry();
			OneTileMoveVector lastToTry = this.currentPosition.getDirection().getOpposite();

			Point p = new Point(currentPosition.getX(), currentPosition.getY());
			TrackPiece tp = map.getTrackPiece(p);
			TrackConfiguration conf = tp.getTrackConfiguration();
			OneTileMoveVector[] vectors = OneTileMoveVector.getList();

			int i = v.getNumber();

			int loopCounter = 0;
			while (!conf.contains(vectors[i].getTemplate())) {
				i++;
				i = i % 8;
				loopCounter++;
				if (8 < loopCounter) {
					throw new IllegalStateException();
					//This should never happen.
				}
			}

			OneTileMoveVector branchDirection = OneTileMoveVector.getInstance(i);
			this.currentBranch.setDirection(branchDirection);
			int x = this.currentPosition.getX() + branchDirection.deltaX;
			int y = this.currentPosition.getY() + branchDirection.deltaY;
			this.currentBranch.setX(x);
			this.currentBranch.setY(y);
		}
		beforeFirst = false;
	}

	/**
	 * @see jfreerails.misc.Explorer#getBranchPosition()
	 */
	public int getBranchPosition() {
		return currentBranch.toInt();
	}

	/**
	 * @see jfreerails.misc.Explorer#getBranchLength()
	 */
	public int getBranchLength() {
		return currentBranch.getDirection().getLength();
	}

	/**
	 * @see jfreerails.misc.Explorer#hasNextBranch()
	 */
	public boolean hasNextBranch() {

		if (beforeFirst) {
			//We can always go back the way we have come, so if we are before the first
			//branch, there must be a branch: the one we used to get here.

			return true;
		} else {

			//Since we can always go back the way we have come, if the direction of 
			//current branch is not equal to the opposite of the current direction,
			//there must be another branch.
			OneTileMoveVector currentBranchDirection = this.currentBranch.getDirection();
			OneTileMoveVector oppositeToCurrentDirection = this.currentPosition.getDirection().getOpposite();

			if (oppositeToCurrentDirection.getNumber() == currentBranchDirection.getNumber()) {
				return false;
			} else {
				return true;
			}
		}
	}

	public NewFlatTrackExplorer(TrackTileMap ttm, PositionOnTrack p) {
		map = ttm;
		this.currentPosition = new PositionOnTrack(p.getX(), p.getY(), p.getDirection());
	}

	public static PositionOnTrack[] getPossiblePositions(TrackTileMap ttm, Point p) {
		TrackPiece tp = ttm.getTrackPiece(p);
		TrackConfiguration conf = tp.getTrackConfiguration();
		OneTileMoveVector[] vectors = OneTileMoveVector.getList();

		//Count the number of possible positions.
		int n = 0;
		for (int i = 0; i < vectors.length; i++) {
			if (conf.contains(vectors[i].getTemplate())) {
				n++;
			}
		}

		PositionOnTrack[] possiblePositions = new PositionOnTrack[n];

		n = 0;
		for (int i = 0; i < vectors.length; i++) {
			if (conf.contains(vectors[i].getTemplate())) {

				possiblePositions[n] = new PositionOnTrack(p.x, p.y, vectors[i].getOpposite());
				n++;
			}
		}
		return possiblePositions;
	}

	OneTileMoveVector getFirstVectorToTry() {
		if (beforeFirst) {

			//Return the vector that is 45 degrees clockwise from the oppposite 
			//of the current position.
			OneTileMoveVector v = this.currentPosition.getDirection();
			v = v.getOpposite();
			int i = v.getNumber();
			i++;
			i = i % 8;
			v = OneTileMoveVector.getInstance(i);
			return v;
		} else {

			//Return the vector that is 45 degrees clockwise from the direction  
			//of the current branch.
			OneTileMoveVector v = this.currentBranch.getDirection();
			int i = v.getNumber();
			i++;
			i = i % 8;
			v = OneTileMoveVector.getInstance(i);
			return v;
		}
	}

}
