package jfreerails.world.terrain;

import java.awt.Point;




final public class City {

 
    private final String name;


    private final Point position;
  

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

  	public City(String name, Point p){
  		this.name=name;
  		position=p;
  	}
}





