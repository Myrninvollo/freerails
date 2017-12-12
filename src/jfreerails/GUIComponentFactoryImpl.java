package jfreerails;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import jfreerails.client.BuildMenu;
import jfreerails.client.GUIComponentFactory;
import jfreerails.client.ViewLists;
import jfreerails.client.menu.StationTypesPopup;
import jfreerails.client.view.DetailMapView;
import jfreerails.client.view.MainMapAndOverviewMapMediator;
import jfreerails.client.view.MapView;
import jfreerails.client.view.MapViewJComponentConcrete;
import jfreerails.client.view.MapViewMoveReceiver;
import jfreerails.client.view.NewOverviewMapJComponent;
import jfreerails.client.view.ZoomedOutMapView;
import jfreerails.controller.MoveChainFork;
import jfreerails.controller.MoveReceiver;
import jfreerails.controller.ServerGameEngine;
import jfreerails.controller.StationBuilder;
import jfreerails.controller.TrackMoveExecutor;
import jfreerails.controller.TrackMoveProducer;
import jfreerails.controller.TrainBuilder;
import jfreerails.world.World;

public class GUIComponentFactoryImpl implements GUIComponentFactory {

	private ViewLists viewLists;
	private World world;
	private MainMapAndOverviewMapMediator mediator;
	private ServerGameEngine gameEngine;

	StationTypesPopup stationTypesPopup;
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
		stationTypesPopup = new StationTypesPopup();
		this.mediator = new MainMapAndOverviewMapMediator(overviewMapContainer, mainMapScrollPane1.getViewport(), mapViewJComponent, r);

	}

	public void setup(ViewLists vl, World w, ServerGameEngine ge) {
		if (!vl.validate(w)) {
			throw new IllegalArgumentException("The specified ViewLists are not comaptible with the specified world!");
		}
		viewLists = vl;
		world = w;
		this.gameEngine = ge;

		//create the main and overview maps
		mainMap = new DetailMapView(world.getMap(), viewLists.getTileViewList(), viewLists.getTrackPieceViewList(), world.getTrainList());
		overviewMap = new ZoomedOutMapView(world.getMap());

		//init the move handlers

		MoveReceiver trackMoveExecutor = new TrackMoveExecutor(world.getMap());
		MoveChainFork moveFork = new MoveChainFork(trackMoveExecutor);

		MoveReceiver overviewmapMoveReceiver = new MapViewMoveReceiver(mainMap);
		moveFork.add(overviewmapMoveReceiver);

		MoveReceiver mainmapMoveReceiver = new MapViewMoveReceiver(overviewMap);
		moveFork.add(mainmapMoveReceiver);

		trackBuilder = new TrackMoveProducer(world.getMap(), moveFork);
		TrainBuilder tb = new TrainBuilder(world, gameEngine);
		StationBuilder sb = new StationBuilder(trackBuilder, world.getTrackRuleList());

		stationTypesPopup.setup(sb);

		//setup the the main and overview map JComponents
		 ((MapViewJComponentConcrete) mapViewJComponent).setup(mainMap, trackBuilder, tb, stationTypesPopup);
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

				gameEngine.newGame(mapName);
				World world = gameEngine.getWorld();
				ViewLists viewLists = getViewLists();

				if (!viewLists.validate(world)) {
					throw new IllegalArgumentException();
				}
				setup(viewLists, world, gameEngine);
			}

		});

		JMenuItem newGameJMenuItem2 = new JMenuItem("New game small map");

		gameMenu.add(newGameJMenuItem2);
		newGameJMenuItem2.addActionListener(new ActionListener() {

			String mapName = "small_south_america.png";

			public void actionPerformed(ActionEvent e) {

				gameEngine.newGame(mapName);
				World world = gameEngine.getWorld();
				ViewLists viewLists = getViewLists();

				if (!viewLists.validate(world)) {
					throw new IllegalArgumentException();
				}
				setup(viewLists, world, gameEngine);
			}

		});

		JMenuItem saveGameJMenuItem = new JMenuItem("Save game");

		gameMenu.add(saveGameJMenuItem);
		saveGameJMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				gameEngine.saveGame();
			}

		});

		JMenuItem loadGameJMenuItem = new JMenuItem("Load game");

		gameMenu.add(loadGameJMenuItem);
		loadGameJMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				gameEngine.loadGame();
				World w = gameEngine.getWorld();

				ViewLists viewLists = getViewLists();

				if (!viewLists.validate(world)) {
					throw new IllegalArgumentException();
				}
				setup(viewLists, w, gameEngine);

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
		return this.viewLists;
	}

	World getWorld() {
		return this.world;
	}

}
