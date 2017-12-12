
/*
* TestScrolling.java
*
* Created on 21 August 2001, 21:40
*/
package jfreerails.experimental;
import java.awt.*;

/**
* 
* @author  Luke Lindsay
* @version 
*/


public class TestScrolling {

    private ExptMapView mapView;

    private ExptMapViewJComponent jComponent;

    private ExptClientJFrame jFrame;

    private ExptBackGroundBuffer bgrdPainter;
    
    /** Creates new TestScrolling */
    
    public TestScrolling() {
        mapView = new ExptMapView();
        bgrdPainter = new ExptBackGroundBuffer( mapView );
        jComponent = new ExptMapViewJComponent( bgrdPainter, mapView );
        jFrame = new ExptClientJFrame( jComponent );
        jFrame.show();
    }
    
    /**
    * @param args the command line arguments
    */
    
    public static void main( String args[] ) {
        new TestScrolling();
    }
}
