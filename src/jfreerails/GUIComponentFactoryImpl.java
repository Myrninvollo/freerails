package jfreerails;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import jfreerails.client.renderer.MapRenderer;
import jfreerails.client.renderer.ViewLists;
import jfreerails.client.renderer.ZoomedOutMapRenderer;
import jfreerails.client.top.BuildMenu;
import jfreerails.client.top.ClientJFrame;
import jfreerails.client.top.GUIComponentFactory;
import jfreerails.client.top.StationTypesPopup;
import jfreerails.client.top.UserInputOnMapController;
import jfreerails.client.view.DetailMapView;
import jfreerails.client.view.DialogueBoxController;
import jfreerails.client.view.FreerailsCursor;
import jfreerails.client.view.MainMapAndOverviewMapMediator;
import jfreerails.client.view.MapViewJComponentConcrete;
import jfreerails.client.view.MapViewMoveReceiver;
import jfreerails.client.view.NewOverviewMapJComponent;
import jfreerails.controller.MoveChainFork;
import jfreerails.controller.MoveReceiver;
import jfreerails.controller.ServerGameEngine;
import jfreerails.controller.StationBuilder;
import jfreerails.controller.TrackMoveExecutor;
import jfreerails.controller.TrackMoveProducer;
import jfreerails.world.top.World;

public class GUIComponentFactoryImpl implements GUIComponentFactory {
    
    //MyGlassPanel glassPanel;
    
    DialogueBoxController dialogueBoxController;
    
    private ViewLists viewLists;
    private World world;
    private MainMapAndOverviewMapMediator mediator;
    private ServerGameEngine gameEngine;
    
    FreerailsCursor cursor;
    UserInputOnMapController userInputOnMapController;
    
    StationTypesPopup stationTypesPopup;
    BuildMenu buildMenu;
    JMenu displayMenu;
    JComponent overviewMapContainer;
    JComponent mainMapContainer;
    MapViewJComponentConcrete mapViewJComponent;
    TrackMoveProducer trackBuilder;
    private JScrollPane mainMapScrollPane1;
    MapRenderer mainMap, overviewMap;
    
    Rectangle r = new Rectangle(10, 10, 10, 10);
    
    JFrame clientJFrame;
    
    public GUIComponentFactoryImpl() {
        userInputOnMapController = new UserInputOnMapController();
        buildMenu = new jfreerails.client.top.BuildMenu();
        mapViewJComponent = new MapViewJComponentConcrete();
        mainMapScrollPane1 = new JScrollPane();
        overviewMapContainer = new NewOverviewMapJComponent(r);
        stationTypesPopup = new StationTypesPopup();
        this.mediator =
        new MainMapAndOverviewMapMediator(
        overviewMapContainer,
        mainMapScrollPane1.getViewport(),
        mapViewJComponent,
        r);
        
        
        //glassPanel = new MyGlassPanel();
        //glassPanel.showContent(new NewsPaperJPanel());
        clientJFrame = new ClientJFrame(this);
        
        //clientJFrame.setGlassPane(glassPanel);
    }
    
    public void setup(ViewLists vl, World w, ServerGameEngine ge) {
        if (!vl.validate(w)) {
            throw new IllegalArgumentException("The specified ViewLists are not comaptible with the specified world!");
        }
        viewLists = vl;
        world = w;
        this.gameEngine = ge;
        
        dialogueBoxController = new DialogueBoxController(clientJFrame, w, vl);
        
        //create the main and overview maps
        mainMap =
        new DetailMapView(
        world,
        viewLists.getTileViewList(),
        viewLists.getTrackPieceViewList()
        );
        overviewMap = new ZoomedOutMapRenderer(world);
        
        //init the move handlers
        
        MoveReceiver trackMoveExecutor = new TrackMoveExecutor(world);
        MoveChainFork moveFork = new MoveChainFork(trackMoveExecutor);
        
        MoveReceiver overviewmapMoveReceiver = new MapViewMoveReceiver(mainMap);
        moveFork.add(overviewmapMoveReceiver);
        
        MoveReceiver mainmapMoveReceiver = new MapViewMoveReceiver(overviewMap);
        moveFork.add(mainmapMoveReceiver);
        
        trackBuilder = new TrackMoveProducer(world, moveFork);
        StationBuilder sb = new StationBuilder(trackBuilder, world);
        
        stationTypesPopup.setup(sb);
        
        this.cursor = new FreerailsCursor(mainMap);
        //setup the the main and overview map JComponents
        
        ((MapViewJComponentConcrete) mapViewJComponent).setup(mainMap, cursor);
        
        dialogueBoxController.setDefaultFocusOwner(mapViewJComponent);
        
        userInputOnMapController.setup(
        mapViewJComponent,
        trackBuilder,
        stationTypesPopup,
        cursor,
        dialogueBoxController);
        
        buildMenu.setup(world, trackBuilder);
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
        displayMenu = new JMenu("Display");
        displayMenu.setMnemonic(68);
        JMenuItem trainOrdersJMenuItem = new JMenuItem("Train Orders");
        trainOrdersJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogueBoxController.showTrainOrders();
            }
        });
        displayMenu.add(trainOrdersJMenuItem);
        return displayMenu;
    }
    
    public JMenu createGameMenu() {
        
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(71);
        
        JMenuItem quitJMenuItem = new JMenuItem("Exit Game");
        quitJMenuItem.setMnemonic(88);
        //gameMenu.add(quitJMenuItem);
        quitJMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        
        JMenuItem newGameJMenuItem = new JMenuItem("New game big map");
        //newGameJMenuItem.setMnemonic(??);
        //gameMenu.add(newGameJMenuItem);
        newGameJMenuItem.addActionListener(new ActionListener() {
            
            String mapName = "south_america.png";
            
            public void actionPerformed(ActionEvent e) {
                
                gameEngine.newGame(OldWorldImpl.createWorldFromMapFile(mapName));
                World world = gameEngine.getWorld();
                ViewLists viewLists = getViewLists();
                
                if (!viewLists.validate(world)) {
                    throw new IllegalArgumentException();
                }
                setup(viewLists, world, gameEngine);
            }
            
        });
        
        JMenuItem newGameJMenuItem2 = new JMenuItem("New game small map");
        //newGameJMenuItem2.setMnemonic(??);
        //gameMenu.add(newGameJMenuItem2);
        newGameJMenuItem2.addActionListener(new ActionListener() {
            
            String mapName = "small_south_america.png";
            
            public void actionPerformed(ActionEvent e) {
                
                gameEngine.newGame(OldWorldImpl.createWorldFromMapFile(mapName));
                World world = gameEngine.getWorld();
                ViewLists viewLists = getViewLists();
                
                if (!viewLists.validate(world)) {
                    throw new IllegalArgumentException();
                }
                setup(viewLists, world, gameEngine);
            }
            
        });
        
        JMenuItem saveGameJMenuItem = new JMenuItem("Save game");
        saveGameJMenuItem.setMnemonic(83);
        //gameMenu.add(saveGameJMenuItem);
        saveGameJMenuItem.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                gameEngine.saveGame();
            }
            
        });
        
        JMenuItem loadGameJMenuItem = new JMenuItem("Load game");
        loadGameJMenuItem.setMnemonic(76);
        //gameMenu.add(loadGameJMenuItem);
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
        
        JMenuItem newspaperJMenuItem = new JMenuItem("Newspaper");
        newspaperJMenuItem.setMnemonic(78);
        //gameMenu.add(newspaperJMenuItem);
        newspaperJMenuItem.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                dialogueBoxController.showNewspaper("Headline");
                //glassPanel.setVisible(true);
            }
            
        });
        
        JMenuItem showControls = new JMenuItem("Show game controls");
        showControls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogueBoxController.showGameControls();
            }
        });
        
        
        gameMenu.add(newGameJMenuItem);
        gameMenu.add(newGameJMenuItem2);
        gameMenu.addSeparator();
        gameMenu.add(loadGameJMenuItem);
        gameMenu.add(saveGameJMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(newspaperJMenuItem);
        gameMenu.add(showControls);
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
    
}
