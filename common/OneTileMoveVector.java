
/*
* OneTileMoveVector.java
*
* Created on 11 July 2001, 12:09
*/
package jfreerails.common;

import java.util.*;
/**
*
* @author  Luke Lindsay
* @version 
*/

import jfreerails.common.exception.FreerailsException;


/** This class encapsulates movements from a tile to any one of the surrounding eight tiles.
 */
public class OneTileMoveVector extends java.lang.Object {
 
  
    
/** North-East
 */    
    public static OneTileMoveVector NORTH_EAST = new OneTileMoveVector(1,-1, null);

/** East
 */    
    public static  OneTileMoveVector EAST = new OneTileMoveVector(1,0, null);

/** South
 */    
    public static  OneTileMoveVector SOUTH = new OneTileMoveVector(0,-1, null);

/** South West
 */    
    public static  OneTileMoveVector SOUTH_WEST = new OneTileMoveVector(-1,1, null);

/** North West
 */    
    public static  OneTileMoveVector NORTH_WEST = new OneTileMoveVector(-1,-1, null);

/** North
 */    
    public static  OneTileMoveVector NORTH = new OneTileMoveVector(0,-1, null);

/** West
 */    
    public static  OneTileMoveVector WEST = new OneTileMoveVector(-1,0, null);

/** South East
 */    
    public static  OneTileMoveVector SOUTH_EAST = new OneTileMoveVector(1,1, null);
   
   
     
   
 
/** The X and Y components of the vector.
 */    
    private int deltaX, deltaY;
    
/** Create a new OneTileMoveVector.  Pass values for delta X and Y: they must be in the range -1 to 1 and cannot both be equal to 0.
 * @param x Tile coordinate.
 * @param y Tile coordinate
 * @throws FreerailsException Thrown when the values passed correspond a movement other than a one tile step.
 */    
    public OneTileMoveVector(int x,int y) throws FreerailsException {
        if( ( (( x < -1 ) || ( x > 1 ) ) || ( ( y < -1 ) || ( y > 1 ) ))|| (( x==0 )&&( y==0 ) )) {
            throw new FreerailsException( "The values passed both must be integers in the range -1 to 1, and not both equal 0." );
        }
        else {
            deltaX=x;
            deltaY=y;
        }
    }
    
/** Constructer identical to the default but does not throw an exception.
 */    
    public OneTileMoveVector(int x,int y,Object o){
        deltaX=x;
        deltaY=y;
    }
        
        
    
/** Creates a new OneTileMoveVector equal in value to the one passed as a reference.
 */    
     public OneTileMoveVector(OneTileMoveVector v) {
        // deltaX=;
        // deltaY=y;
     }
/** Returns the X component of the vector.
 */     
     public int getX(){
         return deltaX;
     }
/** Returns the Y component of the vector.
 */     
     public int getY(){
         return deltaY;
     }

/** Rotates the vector by 45 degree clockwise. (E.g. N->NE).
 */     
     public void rotateClockwise(){
         //TODO add code
     }
/** Rotates the vector by 45 degrees anticlockwise.
 */     
     public void rotateAniClockwise(){
         //TODO add code
     }
     public OneTileMoveVector getOpposite(){
         return  new OneTileMoveVector(this.deltaX*-1,this.deltaY*-1,null);
     }
/** Returns true if the values passed could be used to create a valid vector.
 */     
      public static boolean checkValidity(int x,int y) {
        if( ( ( x < -1 ) || ( x > 1 ) ) || ( ( y < -1 ) || ( y > 1 ) ) ) {
            return false;
        }
        else {
            return true;
        }
      }
      public String getVectorName(){
          String name;
          switch (deltaY) {
              case 1:
                  name=" south";
                  break;
             
              case -1:
                  name=" north";
                  break;
                  
                  default:
                      name="";
                      break;
          }
          switch (deltaX){
              case 1:
              name+=" east";
              break;
              case -1:
                  name+=" west";
                  break;
                  default:
                      break;
          }
          return name;
      }
                      
                      
                  
}
