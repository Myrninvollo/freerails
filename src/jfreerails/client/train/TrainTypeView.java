package jfreerails.client.train;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jfreerails.world.train.WagonType;

public class TrainTypeView {
	
	private static int numberOfTypes=0;
	
	private static final Set set=new HashSet();
	
	private final Color color;
	
	private final int typeNumber;
	

	
	public TrainTypeView(Color c){
		typeNumber=numberOfTypes;
		numberOfTypes++;
		color=c;
		set.add(this);
	}
	
	public static Iterator iterator(){
		return set.iterator();
	}
	public Color getColor(){
		return color;
	}
	public int getTypeNumber(){
		return typeNumber;
	}
	
	public static int getSize(){
		return numberOfTypes;
	}

}
