package experimental;

import jfreerails.GUIComponentFactoryImpl;
import jfreerails.RunFreerails;
import jfreerails.ViewListsImpl;
import jfreerails.WorldImpl;
import jfreerails.client.ViewLists;
import jfreerails.world.World;

public class ExptRunFreerails {

	public static void main(String[] args) {
	
        
        World world = WorldImpl.createWorldFromMapFile("south_america.png");

        ViewLists viewLists = new ViewListsImpl();
        
        GUIComponentFactoryImpl gUIComponentFactory = new GUIComponentFactoryImpl();


        RunFreerails.createClient(false, false, world, viewLists, gUIComponentFactory);

	}
}
