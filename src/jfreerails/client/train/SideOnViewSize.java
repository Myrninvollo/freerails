package jfreerails.client.train;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SideOnViewSize  {
	
	private static final Set set=new HashSet();
	
	public static final SideOnViewSize BIG=new SideOnViewSize(30);
	
	public static final SideOnViewSize SMALL=new SideOnViewSize(20);
	
	public static final SideOnViewSize TINY=new SideOnViewSize(10);
	
	
	
	private final double scale;
	
	
	private SideOnViewSize(double s){
		scale=s;
		set.add(this);
	}
	
	public static Iterator iterator(){
		return set.iterator();
	}
	public int getWidth(){
		return (int)scale;
	}
	
	public int getHeight(){
		return (int)scale/2;
	}

}
