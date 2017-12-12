
/*
* TerrainMapIO.java
*
* Created on 28 May 2001, 00:26
*/
package jfreerails.common;

/**
* This class provides methods to load and save terrain maps.
*
* @author  lindsal8
* @version 
*/
import java.awt.Color;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.net.URL;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.TerrainMap;


public class TerrainMapIO extends Object {
    
    /** Creates new TerrainMapIO */
    
    public TerrainMapIO() {
        
    }
    
    //
    
    public static TerrainMap load_map( URL url ) throws FreerailsException {
        try {
            System.out.println( "\nLoading map: " + url );
            TerrainMap  map = new TerrainMap( ImageIO.read( url ) );
            return map;
        }
        catch( Exception e ) {
            throw new FreerailsException( "Error loading map: " + url );
        }
    }

/*
public static int save_map(short[][] map,String filename){
try{    
FileOutputStream out = new FileOutputStream(filename);
ObjectOutputStream s = new ObjectOutputStream(out);
s.writeObject(map);          
s.flush();      
System.out.println("\nMap saved as: "+filename);
}

catch (Exception e){
System.out.println("Error saving map");
System.out.println(e);
return -1;
} 
return 1;

}
*/
}
