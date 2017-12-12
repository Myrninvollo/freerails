package jfreerails.client.train;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TrainTypeView {
	
	private static int numberOfTypes=0;
	
	private static final Set set=new HashSet();
	
	public static final TrainTypeView MAIL=new TrainTypeView(Color.WHITE);
	
	public static final TrainTypeView PASSENGER=new TrainTypeView(Color.BLUE);
	
	public static final TrainTypeView FAST_FREIGHT=new TrainTypeView(Color.YELLOW);
	
	public static final TrainTypeView SLOW_FREIGHT=new TrainTypeView(Color.ORANGE);
	
	public static final TrainTypeView BULK_FREIGHT=new TrainTypeView(Color.BLACK);
	
	public static final TrainTypeView ENGINE=new TrainTypeView(Color.LIGHT_GRAY);
	
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
