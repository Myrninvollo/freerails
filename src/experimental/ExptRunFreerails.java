package experimental;

import jfreerails.RunFreerails;
import jfreerails.client.GUIComponentFactoryImpl;
import jfreerails.client.ViewLists;
import jfreerails.client.ViewListsImpl;
import jfreerails.world.World;
import jfreerails.world.WorldImpl;

public class ExptRunFreerails {

	public static void main(String[] args) {
	
        
        World world = WorldImpl.createWorldFromMapFile("south_america.png");

        ViewLists viewLists = new ViewListsImpl();
        
        GUIComponentFactoryImpl gUIComponentFactory = new GUIComponentFactoryImpl();


        RunFreerails.createClient(false, false, world, viewLists, gUIComponentFactory);

	}
}
