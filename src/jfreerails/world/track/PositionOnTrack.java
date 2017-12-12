package jfreerails.world.track;

public final class PositionOnTrack {

	private final int x;

	private final int y;

	private final int z;

	private final int direction;

	public PositionOnTrack(int x, int y, int z, int direction) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.direction = direction;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getZ() {
		return z;
	}
	public int getDirection() {
		return direction;
	}

}