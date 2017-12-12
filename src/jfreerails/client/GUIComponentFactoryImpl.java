package jfreerails.client;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import jfreerails.client.view.map.DetailMapView;
import jfreerails.client.view.map.MapView;
import jfreerails.client.view.map.MapViewJComponentConcrete;
import jfreerails.client.view.map.MapViewMoveReceiver;
import jfreerails.client.view.map.NewOverviewMapJComponent;
import jfreerails.client.view.map.ZoomedOutMapView;
import jfreerails.move.receiver.MoveChainFork;
import jfreerails.move.receiver.MoveReceiver;
import jfreerails.move.receiver.TrackMoveExecutor;
import jfreerails.move.source.TrackMoveProducer;
import jfreerails.world.World;
import jfreerails.world.WorldImpl;

import experimental.mapview.MainMapAndOverviewMapMediator;

public class GUIComponentFactoryImpl implements GUIComponentFactory {

	private ViewLists ViewLists;
	private World world;
	private MainMapAndOverviewMapMediator mediator;

	BuildMenu buildMenu;
	JComponent overviewMapContainer;
	JComponent mainMapContainer;
	JComponent mapViewJComponent;
	TrackMoveProducer trackBuilder;
	private JScrollPane mainMapScrollPane1;
	MapView mainMap, overviewMap;

	Rectangle r = new Rectangle(10, 10, 10, 10);

	public GUIComponentFactoryImpl() {
		buildMenu = new jfreerails.client.BuildMenu();
		mapViewJComponent = new MapViewJComponentConcrete();
		mainMapScrollPane1 = new JScrollPane();
		overviewMapContainer = new NewOverviewMapJComponent(r);
		this.mediator =
			new MainMapAndOverviewMapMediator(
				overviewMapContainer,
				mainMapScrollPane1.getViewport(),
				mapViewJComponent,
				r);

	}

	public void setup(ViewLists vl, World w) {
		if (!vl.validate(w)) {
			throw new IllegalArgumentException("The specified ViewLists are not comaptible with the specified world!");
		}
		ViewLists = vl;
		world = w;

		//create the main and overview maps
		mainMap =
			new DetailMapView(
				world.getMap(),
				ViewLists.getTileViewList(),
				ViewLists.getTrackPieceViewList(),
				world.getTrainList());
		overviewMap = new ZoomedOutMapView(world.getMap());

		//init the move handlers
		MoveReceiver trackMoveExecutor = new TrackMoveExecutor(world.getMap());
		MoveReceiver mapViewMoveReceiver = new MapViewMoveReceiver(overviewMap);
		MoveReceiver moveFork = new MoveChainFork(trackMoveExecutor, mapViewMoveReceiver);
		trackBuilder = new TrackMoveProducer(world.getMap(), moveFork);

		//setup the the main and overview map JComponents
		 ((MapViewJComponentConcrete) mapViewJComponent).setup(mainMap, trackBuilder);
		buildMenu.setup(world.getTrackRuleList(), trackBuilder);
		mainMapScrollPane1.setViewportView(this.mapViewJComponent);
		((NewOverviewMapJComponent) overviewMapContainer).setup(overviewMap);
	}

	public JComponent createOverviewMap() {
		return overviewMapContainer;
	}

	public JComponent createMainMap() {

		return mainMapScrollPane1;

	}

	public JLabel createMessagePanel() {
		return new JLabel("Message");
	}

	public JMenu createBuildMenu() {
		return buildMenu;
	}

	public JMenu createDisplayMenu() {
		return new JMenu("Display");
	}

	public JMenu createGameMenu() {

		JMenu gameMenu = new JMenu("Game");

		JMenuItem quitJMenuItem = new JMenuItem("Quit");

		gameMenu.add(quitJMenuItem);

		quitJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		JMenuItem newGameJMenuItem = new JMenuItem("New game big map");

		gameMenu.add(newGameJMenuItem);
		newGameJMenuItem.addActionListener(new ActionListener() {

			String mapName = "south_america.png";

			public void actionPerformed(ActionEvent e) {

				World world = WorldImpl.createWorldFromMapFile(mapName);
				ViewLists viewLists = getViewLists();
				if (!viewLists.validate(world)) {
					throw new IllegalArgumentException();
				}
				setup(viewLists, world);
			}

		});

		JMenuItem newGameJMenuItem2 = new JMenuItem("New game small map");

		gameMenu.add(newGameJMenuItem2);
		newGameJMenuItem2.addActionListener(new ActionListener() {

			String mapName = "small_south_america.png";

			public void actionPerformed(ActionEvent e) {

				World world = WorldImpl.createWorldFromMapFile(mapName);
				ViewLists viewLists = getViewLists();
				if (!viewLists.validate(world)) {
					throw new IllegalArgumentException();
				}
				setup(viewLists, world);
			}

		});

		JMenuItem saveGameJMenuItem = new JMenuItem("Save game");

		gameMenu.add(saveGameJMenuItem);
		saveGameJMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					System.out.print("Saving game..  ");
					FileOutputStream out = new FileOutputStream("freerails.sav");
					GZIPOutputStream zipout = new GZIPOutputStream(out);
					
					ObjectOutputStream objectOut = new ObjectOutputStream(zipout);
					objectOut.writeObject(getWorld());
					objectOut.flush();					
      				objectOut.close();
      				                 
					System.out.println("done.");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		});

		JMenuItem loadGameJMenuItem = new JMenuItem("Load game");

		gameMenu.add(loadGameJMenuItem);
		loadGameJMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					System.out.print("Loading game..  ");
					FileInputStream in = new FileInputStream("freerails.sav");
					GZIPInputStream zipin=new GZIPInputStream(in);
					ObjectInputStream objectIn = new ObjectInputStream(zipin);

					World world = (World) objectIn.readObject();
					ViewLists viewLists = getViewLists();
					if (!viewLists.validate(world)) {
						throw new IllegalArgumentException();
					}
					setup(viewLists, world);
					System.out.println("done.");
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		return gameMenu;
	}
	private void addMainMapAndOverviewMapMediatorIfNecessary() {
		//if (this.mainMapContainer != null
		//	&& this.overviewMapContainer != null
		//	&& null == this.mediator) {
		//	//Rectangle r = this.overviewMapContainer.getMainMapVisibleRect();
		//	
		//}
	}

	ViewLists getViewLists() {
		return this.ViewLists;
	}

	World getWorld() {
		return this.world;
	}

}
