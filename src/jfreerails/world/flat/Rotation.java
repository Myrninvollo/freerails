package jfreerails.world.flat;

public class Rotation {

	int i;
	public static final Rotation BY_0_DEGREES = new Rotation(0);
	public static final Rotation BY_45_DEGREES = new Rotation(1);
	public static final Rotation BY_90_DEGREES = new Rotation(2);
	public static final Rotation BY_135_DEGREES = new Rotation(3);
	
	public static final Rotation BY_180_DEGREES = new Rotation(4);
	public static final Rotation BY_225_DEGREES = new Rotation(5);
	public static final Rotation BY_270_DEGREES = new Rotation(6);
	public static final Rotation BY_315_DEGREES = new Rotation(7);

	private Rotation(int _i){
		i=_i;		
	}
}
