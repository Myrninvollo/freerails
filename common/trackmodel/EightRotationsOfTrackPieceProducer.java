
/*
* LegalTrackPieceProducer.java
*
* Created on 20 July 2001, 00:06
*/
package jfreerails.common.trackmodel;
import jfreerails.common.OneTileMoveVector;
import jfreerails.common.TileModel;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.IntPoint;

/**
*
* @author  Luke Lindsay
* @version 
*/


public class EightRotationsOfTrackPieceProducer extends java.lang.Object {
    
    /** Creates new LegalTrackPieceProducer */
    
    public EightRotationsOfTrackPieceProducer() {
        
    }
    
    public static int[] getRotations( int trackBlueprint ) {
        int  trackTemplate = trackBlueprint;
        int[]  derivedTrackPieces = new int[ 8 ];
        for( int  i = 0;i < 8;i++ ) {
            derivedTrackPieces[ i ] = trackTemplate;
            boolean[][]  trackTemplateBooleanArray = getTrackBooleanArray( trackTemplate );
            trackTemplateBooleanArray = rotateTrackNodeClockwise( trackTemplateBooleanArray );
            trackTemplate = getTrackGraphicNumber( trackTemplateBooleanArray );
        }
        return derivedTrackPieces;
    }
    
    private static int getTrackGraphicNumber( boolean[][] railsList ) {
        int  trackGraphicNumber = 0;
        for( int  y = 0;y < 3;y++ ) {
            for( int  x = 0;x < 3;x++ ) {
                if( railsList[ x ][ y ] == true ) {
                    trackGraphicNumber = trackGraphicNumber | ( 1 << ( 3 * y + x ) );
                }
            }
        }
        return trackGraphicNumber;
    }
    
    private static boolean[][] rotateTrackNodeClockwise( boolean[][] source ) {
        IntPoint[][]  grabValueFrom = new IntPoint[ 3 ][];
        grabValueFrom[ 0 ] = new IntPoint[] {
            new IntPoint( 0, 1 ), new IntPoint( 0, 0 ), new IntPoint( 1, 0 )
        };
        grabValueFrom[ 1 ] = new IntPoint[] {
            new IntPoint( 0, 2 ), new IntPoint( 1, 1 ), new IntPoint( 2, 0 )
        };
        grabValueFrom[ 2 ] = new IntPoint[] {
            new IntPoint( 1, 2 ), new IntPoint( 2, 2 ), new IntPoint( 2, 1 )
        };
        
        /* I think there is a neater way of doing this, let me know if you know it!  Luke
        */
        boolean[][]  output = new boolean[ 3 ][ 3 ];
        for( int  y = 0;y < 3;y++ ) {
            for( int  x = 0;x < 3;x++ ) {
                IntPoint  point = grabValueFrom[ y ][ x ];
                
                /*y,x because of the way I defined grabValueFrom[][] above.
                */
                output[ x ][ y ] = source[ point.getX() ][ point.getY() ];
            }
        }
        return output;
    }
    
    private static boolean[][] getTrackBooleanArray( int trackGraphicInt ) {
        boolean[][]  trackBooleanArray = new boolean[ 3 ][ 3 ];
        for( int  y = 0;y < 3;y++ ) {
            for( int  x = 0;x < 3;x++ ) {
                if( ( ( trackGraphicInt >> ( 3 * y + x ) ) & 1 ) == 1 ) {
                    trackBooleanArray[ x ][ y ] = true;
                }
            }
        }
        return trackBooleanArray;
    }
}
