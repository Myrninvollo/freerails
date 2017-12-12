package jfreerails;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import jfreerails.client.renderer.MapRenderer;
import jfreerails.client.renderer.ViewLists;
import jfreerails.client.renderer.ZoomedOutMapRenderer;
import jfreerails.client.top.BuildMenu;
import jfreerails.client.top.ClientJFrame;
import jfreerails.client.top.GUIComponentFactory;
import jfreerails.client.top.StationTypesPopup;
import jfreerails.client.top.UserInputOnMapController;
import jfreerails.client.view.TrainsJTabPane;
import jfreerails.client.view.CashJLabel;
import jfreerails.client.view.DateJLabel;
import jfreerails.client.view.DetailMapView;
import jfreerails.client.view.DialogueBoxController;
import jfreerails.client.view.FreerailsCursor;
import jfreerails.client.view.MainMapAndOverviewMapMediator;
import jfreerails.client.view.MapViewJComponentConcrete;
import jfreerails.client.view.MapViewMoveReceiver;
import jfreerails.client.view.OverviewMapJComponent;
import jfreerails.controller.MoveChainFork;
import jfreerails.controller.MoveReceiver;
import jfreerails.controller.StationBuilder;
import jfreerails.controller.MoveExecuter;
import jfreerails.controller.TrackMoveProducer;
import jfreerails.server.ServerGameEngine;
import jfreerails.world.top.World;

public class GUIComponentFactoryImpl implements GUIComponentFactory {

	private DateJLabel datejLabel;
	private CashJLabel cashjLabel;
	
	/**
	 * This is the panel at the bottom right of the screen
	 */
	private TrainsJTabPane trainsJTabPane;

	private javax.swing.JMenu helpMenu;
	private javax.swing.JLabel messageJLabel;

	private final DialogueBoxController dialogueBoxController;

	private ViewLists viewLists;
	private World world;
	private MainMapAndOverviewMapMediator mediator;
	private ServerGameEngine gameEngine;

	FreerailsCursor cursor;
	UserInputOnMapController userInputOnMapController;

	StationTypesPopup stationTypesPopup;
	BuildMenu buildMenu;
	JMenu displayMenu;
	JPanel overviewMapContainer;
	JScrollPane mainMapContainer;
	MapViewJComponentConcrete mapViewJComponent;
	TrackMoveProducer trackBuilder;
	private JScrollPane mainMapScrollPane1;
	MapRenderer overviewMap;
	DetailMapView mainMap;

	Rectangle r = new Rectangle(10, 10, 10, 10);

	JFrame clientJFrame;

	public GUIComponentFactoryImpl() {
		userInputOnMapController = new UserInputOnMapController();
		buildMenu = new jfreerails.client.top.BuildMenu();
		mapViewJComponent = new MapViewJComponentConcrete();
		mainMapScrollPane1 = new JScrollPane();
		overviewMapContainer = new OverviewMapJComponent(r);
		stationTypesPopup = new StationTypesPopup();
		this.mediator =
			new MainMapAndOverviewMapMediator(
				overviewMapContainer,
				mainMapScrollPane1.getViewport(),
				mapViewJComponent,
				r);

		//glassPanel = new MyGlassPanel();
		//glassPanel.showContent(new NewsPaperJPanel());

		//clientJFrame.setGlassPane(glassPanel);

		trainsJTabPane = new TrainsJTabPane();
		datejLabel = new DateJLabel();

		cashjLabel = new CashJLabel();
		messageJLabel = new javax.swing.JLabel("Message");

		clientJFrame = new ClientJFrame(this);
		dialogueBoxController = new DialogueBoxController(clientJFrame);
		
	}

	public void setup(ViewLists vl, World w, ServerGameEngine ge) {
		if (!vl.validate(w)) {
			throw new IllegalArgumentException("The specified ViewLists are not comaptible with the specified world!");
		}
		viewLists = vl;
		world = w;
		this.gameEngine = ge;

		dialogueBoxController.setup(w, vl);

		//create the main and overview maps
		mainMap = new DetailMapView(world, viewLists);
		overviewMap = new ZoomedOutMapRenderer(world);

		//init the move handlers

		MoveReceiver overviewmapMoveReceiver = new MapViewMoveReceiver(mainMap);

		MoveChainFork moveFork = MoveChainFork.getMoveChainFork();
		moveFork.setPrimaryReceiver (overviewmapMoveReceiver);

		MoveExecuter trackMoveExecutor = MoveExecuter.getMoveExecuter();

		MoveReceiver mainmapMoveReceiver = new MapViewMoveReceiver(overviewMap);
		moveFork.add(mainmapMoveReceiver);

		trackBuilder = new TrackMoveProducer(world, trackMoveExecutor);
		StationBuilder sb = new StationBuilder(trackMoveExecutor, world);

		stationTypesPopup.setup(sb, mainMap.getStationRadius());
		FreerailsCursor.initCursor(mainMap);
		this.cursor = FreerailsCursor.getCursor();
		//setup the the main and overview map JComponents

		((MapViewJComponentConcrete) mapViewJComponent).setup(mainMap, cursor);

		dialogueBoxController.setDefaultFocusOwner(mapViewJComponent);

		userInputOnMapController.setup(
			mapViewJComponent,
			trackBuilder,
			stationTypesPopup,
			cursor,
			dialogueBoxController,
			trackMoveExecutor);

		buildMenu.setup(world, trackBuilder);
		mainMapScrollPane1.setViewportView(this.mapViewJComponent);
		System.out.println("Viewport was set");
		((OverviewMapJComponent) overviewMapContainer).setup(overviewMap);

		datejLabel.setup(w, null, null);
		cashjLabel.setup(w, null, null);
		trainsJTabPane.setup(cursor, w, vl);
	}

	public JPanel createOverviewMap() {
		return overviewMapContainer;
	}

	public JScrollPane createMainMap() {
		return mainMapScrollPane1;
	}

	public JMenu createBuildMenu() {
		return buildMenu;
	}

	public JMenu createDisplayMenu() {
		displayMenu = new JMenu("Display");
		displayMenu.setMnemonic(68);
		JMenuItem trainOrdersJMenuItem = new JMenuItem("Train Orders");
		trainOrdersJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogueBoxController.showTrainOrders();
			}
		});

		JMenuItem stationInfoJMenuItem = new JMenuItem("Station Info");
		stationInfoJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dialogueBoxController.showStationInfo(0);
			}
		});
		//		I've moved the processing to the menu item above, LL		
		//		JMenuItem stationInfoCalculations = new JMenuItem("Calculate Station Info");
		//		stationInfoCalculations.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				CalcSupplyAtStations cSAS = new CalcSupplyAtStations(world);
		//				cSAS.doProcessing();
		//			}
		//		});

		displayMenu.add(trainOrdersJMenuItem);
		displayMenu.add(stationInfoJMenuItem);
		//displayMenu.add(stationInfoCalculations);

		return displayMenu;
	}

	public JMenu createGameMenu() {

		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(71);

		JMenuItem quitJMenuItem = new JMenuItem("Exit Game");
		quitJMenuItem.setMnemonic(88);

		quitJMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		JMenuItem newGameJMenuItem = new JMenuItem("New game big map");

		newGameJMenuItem.addActionListener(new ActionListener() {
			String mapName = "south_america";

			public void actionPerformed(ActionEvent e) {
			    gameEngine.newGame(OldWorldImpl.createWorldFromMapFile(mapName));
			    worldModelChanged();
			}
		});

		JMenuItem newGameJMenuItem2 = new JMenuItem("New game small map");

		newGameJMenuItem2.addActionListener(new ActionListener() {
			String mapName = "small_south_america";

			public void actionPerformed(ActionEvent e) {
				gameEngine.newGame(
					OldWorldImpl.createWorldFromMapFile(mapName));
					worldModelChanged();
				}

		});

		JMenuItem saveGameJMenuItem = new JMenuItem("Save game");
		saveGameJMenuItem.setMnemonic(83);

		saveGameJMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				gameEngine.saveGame();
			}

		});

		JMenuItem loadGameJMenuItem = new JMenuItem("Load game");
		loadGameJMenuItem.setMnemonic(76);

		loadGameJMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				gameEngine.loadGame();
				worldModelChanged();
			}

		});

		JMenuItem newspaperJMenuItem = new JMenuItem("Newspaper");
		newspaperJMenuItem.setMnemonic(78);

		newspaperJMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dialogueBoxController.showNewspaper("Headline");
				//glassPanel.setVisible(true);
			}

		});

		//Set up the gamespeed submenu.
		ButtonGroup group = new ButtonGroup();
		JMenu gameSpeedSubMenu = new JMenu("Game Speed...");

		JRadioButtonMenuItem paused = new JRadioButtonMenuItem("Frozen");
		group.add(paused);
		gameSpeedSubMenu.add(paused);
		paused.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEngine.setTargetTicksPerSecond(0);
			}
		});

		JRadioButtonMenuItem slow = new JRadioButtonMenuItem("Slow");
		group.add(slow);
		gameSpeedSubMenu.add(slow);
		slow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEngine.setTargetTicksPerSecond(10);
			}
		});

		JRadioButtonMenuItem moderate = new JRadioButtonMenuItem("Moderate");
		group.add(moderate);
		gameSpeedSubMenu.add(moderate);
		moderate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEngine.setTargetTicksPerSecond(30);
			}
		});

		//Set the initial game speed to moderate.
		moderate.setSelected(true);		

		JRadioButtonMenuItem fast = new JRadioButtonMenuItem("Fast");
		group.add(fast);
		gameSpeedSubMenu.add(fast);
		fast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEngine.setTargetTicksPerSecond(50);
			}
		});

		JRadioButtonMenuItem turbo = new JRadioButtonMenuItem("Turbo");
		group.add(turbo);
		gameSpeedSubMenu.add(turbo);
		turbo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEngine.setTargetTicksPerSecond(50);
			}
		});

		gameMenu.add(newGameJMenuItem);
		gameMenu.add(newGameJMenuItem2);
		gameMenu.addSeparator();
		gameMenu.add(loadGameJMenuItem);
		gameMenu.add(saveGameJMenuItem);
		gameMenu.addSeparator();
		gameMenu.add(gameSpeedSubMenu);
		gameMenu.add(newspaperJMenuItem);
		gameMenu.addSeparator();
		gameMenu.add(quitJMenuItem);

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

	World getAddTrackRules() {
		return this.world;
	}

	public JFrame createClientJFrame() {
		return clientJFrame;

	}

	public JMenu createHelpMenu() {

		helpMenu = new javax.swing.JMenu("Help");
		JMenuItem showControls = new JMenuItem("Show game controls");
		showControls.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dialogueBoxController.showGameControls();
			}
		});

		helpMenu.add(showControls);
		return helpMenu;
	}

	public JTabbedPane createTrainsJTabPane() {
		return trainsJTabPane;
	}

	public JLabel createCashJLabel() {
		return cashjLabel;
	}

	public JLabel createMessagePanel() {
		return messageJLabel;
	}

	public JLabel createDateJLabel() {
		return datejLabel;
	}

	private void worldModelChanged() {
	    /*
	     * XXX this is temporary - we should have a formal object to store
	     * the clients copy of the model, connections to the server, etc.
	     */
	    World world = gameEngine.getWorld();
	    ViewLists viewLists = getViewLists();

	    if (!viewLists.validate(world)) {
		throw new IllegalArgumentException();
	    }
	    MoveChainFork.init();
	    MoveChainFork mr =
		MoveChainFork.getMoveChainFork();
	    MoveExecuter.init(world, mr);
	    setup(viewLists, world, gameEngine);
	}
}
