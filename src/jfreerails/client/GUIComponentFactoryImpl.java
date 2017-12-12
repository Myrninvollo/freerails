package jfreerails.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import jfreerails.client.view.map.BackgroundMapView;
import jfreerails.client.view.map.MapViewJComponent;
import jfreerails.client.view.map.MapViewJComponentConcrete;
import jfreerails.client.view.map.MapViewJComponentContainer;
import jfreerails.client.view.map.NewMapView;
import jfreerails.client.view.map.ZoomedOutMapView;
import jfreerails.move.receiver.MoveChainFork;
import jfreerails.move.receiver.MoveReceiver;
import jfreerails.move.receiver.TrackMoveExecutor;
import jfreerails.move.source.TrackMoveProducer;
import jfreerails.world.World;

public class GUIComponentFactoryImpl implements GUIComponentFactory {
	
	private final ViewLists ViewLists;
	private final World world;
	
	BuildMenu buildMenu;
	MapViewJComponentContainer overviewMapContainer;
	MapViewJComponentContainer mainMapContainer;
	MapViewJComponent mapViewJComponent;
	TrackMoveProducer trackBuilder;
	
	public GUIComponentFactoryImpl(ViewLists vl, World w){
		if(!vl.validate(w)){
			throw new IllegalArgumentException("The specified ViewLists are not comaptible with the specified world!");	
		}
		ViewLists=vl;
		world=w;
		
		//Create the object that controls building track.
			MoveReceiver trackMoveExecutor = new TrackMoveExecutor(world.getMap());

			//Create the visual components to add to the client window.		
			NewMapView mainMap, overviewMap;
			overviewMap = new ZoomedOutMapView(world.getMap());
			mainMap = new BackgroundMapView(world.getMap(), ViewLists.getTileViewList(), ViewLists.getTrackPieceViewList());

			MoveReceiver moveFork =
				new MoveChainFork(
					trackMoveExecutor,
					(MoveReceiver) overviewMap);

			 trackBuilder =
				new TrackMoveProducer(world.getMap(), moveFork);

			 mapViewJComponent =
				new MapViewJComponentConcrete(mainMap, trackBuilder);

			 mainMapContainer =
				MapViewJComponentContainer.generateMainmapComponent(
					mapViewJComponent);
			 overviewMapContainer =
				MapViewJComponentContainer.generateOverviewMapComponent(
					overviewMap,
					mainMapContainer);

			 buildMenu =
				new jfreerails.client.BuildMenu(world.getTrackRuleList(), trackBuilder);

		
	}

	public JComponent createOverviewMap() {
		return overviewMapContainer;
	}

	public JComponent createMainMap() {
		return mainMapContainer;
	}

	public JComponent createMessagePanel() {
		return new JLabel("Message");
	}

	public JMenu createBuildMenu() {
		return buildMenu;
	}

	public JMenu createGameMenu() {
		
		JMenuItem quitJMenuItem = new JMenuItem("Quit");
		JMenu gameMenu = new JMenu("Game");
		gameMenu.add(quitJMenuItem);
		
		quitJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		
		});
		return gameMenu;
	}

}
