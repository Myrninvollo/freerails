
/*
* OneTileMoveVector.java
*
* Created on 11 July 2001, 12:09
*/
package jfreerails.misc;

import java.awt.Point;


/**
*
* @author  Luke Lindsay
* @version 
*/

/** This class encapsulates movements from a tile to any one of the surrounding eight tiles.
*/


public class OneTileMoveVector extends java.lang.Object {



    /** North   */
    public static final OneTileMoveVector NORTH;

    
    /** West    */
    public static final OneTileMoveVector WEST ;

    
    /** South East    */
    public static final OneTileMoveVector SOUTH_EAST;

    
    /** North-East    */
    public static final OneTileMoveVector NORTH_EAST ;

    
    /** East    */
    public static final  OneTileMoveVector EAST ;

    
    /** South    */
    public static final  OneTileMoveVector SOUTH;

    
    /** South West    */
    public static final  OneTileMoveVector SOUTH_WEST;

    
    /** North West    */
    public static final OneTileMoveVector NORTH_WEST;

    private static OneTileMoveVector[][] vectors;

        static {
            vectors =new  OneTileMoveVector[3][3];
            for (int x=-1;x<=1;x++){
                for(int y=-1;y<=1;y++){
                    if((0!=x)||(0!=y)){
                        vectors[x+1][y+1]  = new OneTileMoveVector(x, y);
                    }
                }
            }
            NORTH =getInstance( 0, -1);
            WEST = getInstance( -1, 0);
            SOUTH_EAST = getInstance(1,1);
            NORTH_EAST = getInstance( 1, -1);
            EAST = getInstance( 1, 0);
            SOUTH = getInstance( 0, 1);
            SOUTH_WEST = getInstance( -1, 1);
            NORTH_WEST = getInstance( -1, -1);
        }


    /** The X and Y components of the vector.    */
    public final int deltaX, deltaY;
    


    /** Returns the X component of the vector.    */
    
    public int getX() {
        return deltaX;
    }
    
    /** Returns the Y component of the vector.    */
    
    public int getY() {
        return deltaY;
    }
    
    
    
    /** Returns a new oneTileMoveVector whose direction is
    * opposite to that the current one.
    * @return A oneTileMoveVector.
    */
    
    public OneTileMoveVector getOpposite() {
        return new OneTileMoveVector( this.deltaX * -1, this.deltaY * -1);
    }
    
    /** Returns the name of the vector.  E.g. "north-east"
    * @return the name.
    */
    
    public String getVectorName() {
        String  name;
        switch( deltaY ) {
          case 1:
              name = " south";
              break;
          
          case -1:
              name = " north";
              break;
          
          default:
              name = "";
              break;
        }
        switch( deltaX ) {
          case 1:
              name += " east";
              break;
          
          case -1:
              name += " west";
              break;
          
          default:
              break;
        }
        return name;
    }
    
    /** Create a new OneTileMoveVector.  
     *N.B Private constuctor to enforce singleton like property, use getInstance(x,y) instead.
     *Pass values for delta X and Y: they must be in the range -1 to 1 and cannot both be equal to 0.
    * @param x Tile coordinate.
    * @param y Tile coordinate    
    */    
    private OneTileMoveVector( int x, int y ) {

            deltaX = x;
            deltaY = y;
    }

    public static OneTileMoveVector getInstance(int x, int y){

        if((  ( ( x < -1 ) || ( x > 1 ) ) || ( ( y < -1 ) || ( y > 1 ) ) ) || ( ( x == 0 ) && ( y == 0 ) ))  {
            throw new java.lang.IllegalArgumentException( "The values passed both must be integers in the range -1 to 1, and not both equal 0." );
        }   else{

        return vectors[x+1][y+1];
        }
    }
    /** Returns true if the values passed could be used to create a valid vector.
    */
    
    public static boolean checkValidity( int x, int y ) {
        if( ( ( ( x < -1 ) || ( x > 1 ) ) || ( ( y < -1 ) || ( y > 1 ) ) ) || ( ( x == 0 ) && ( y == 0 ) ) ) {
            return false;
        }
        else {
            return true;
        }
    }
    public Point createRelocatedPoint(Point from){
    	return new Point(from.x+deltaX, from.y+deltaY);
    }
}
