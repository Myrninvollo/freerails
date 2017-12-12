/*
 * RunMe.java
 *
 * Created on 23 June 2002, 02:44
 */

package experimental.mapview;
import javax.swing.JFrame;
import jfreerails.lib.GameLoop;
import jfreerails.lib.ScreenHandler;
/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class RunMe {

    /** Creates new RunMe */
    public RunMe() {
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
       JFrame jFrame = new jfreerails.client.ClientJFrame(new SimpleComponentFactoryImpl2());
       //jFrame.show();
       ScreenHandler screenHandler = new ScreenHandler(jFrame, true);
        GameLoop gameLoop = new GameLoop(screenHandler);
        Thread t = new Thread(gameLoop);
        t.start();
    }

}
