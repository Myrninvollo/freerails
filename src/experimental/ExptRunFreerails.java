package experimental;

import jfreerails.GUIComponentFactoryImpl;
import jfreerails.OldWorldImpl;
import jfreerails.RunFreerails;
import jfreerails.ViewListsImpl;
import jfreerails.client.top.ViewLists;
import jfreerails.world.top.World;

public class ExptRunFreerails {

	public static void main(String[] args) {
	
        
        World world = OldWorldImpl.createWorldFromMapFile("south_america.png");

        ViewLists viewLists = new ViewListsImpl();
        
        GUIComponentFactoryImpl gUIComponentFactory = new GUIComponentFactoryImpl();


        RunFreerails.createClient(false, false, world, viewLists, gUIComponentFactory);

	}
}
