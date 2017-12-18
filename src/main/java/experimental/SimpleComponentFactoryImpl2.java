/*
 * SimpleComponentFactoryImpl2.java
 *
 * Created on 23 June 2002, 02:36
 */
package experimental;

import freerails.client.renderer.BlankMapRenderer;
import freerails.client.view.MainMapAndOverviewMapMediator;
import freerails.client.view.MapViewJComponentConcrete;
import freerails.client.view.OverviewMapJComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This GUIComponentFactory creates simple components that can be used to test
 * the layout of the client jFrame without running the whole game.
 *
 * @author Luke Lindsay
 */
public class SimpleComponentFactoryImpl2 implements
        freerails.client.top.GUIComponentFactory {
    private final Rectangle r = new Rectangle();
    private OverviewMapJComponent overviewMap;
    private JScrollPane mainMapScrollPane1;
    private MapViewJComponentConcrete mainMap;
    private MainMapAndOverviewMapMediator mediator;

    /**
     * Creates new SimpleComponentFactoryImpl
     */
    public SimpleComponentFactoryImpl2() {
    }

    /**
     *
     * @return
     */
    public JMenu createBuildMenu() {
        return new JMenu("Build");
    }

    /**
     *
     * @return
     */
    public JMenu createGameMenu() {
        return new JMenu("Game");
    }

    /**
     *
     * @return
     */
    public JMenu createDisplayMenu() {
        JMenu displayMenu = new JMenu("Display");

        addMainmapzoomMenuItem(displayMenu, 5);
        addMainmapzoomMenuItem(displayMenu, 10);

        addOverviewmapzoomMenuItem(displayMenu, 0.2F);
        addOverviewmapzoomMenuItem(displayMenu, 0.6F);

        return displayMenu;
    }

    /**
     *
     * @return
     */
    public JMenu createBrokerMenu() {
        return new JMenu("Broker");
    }

    private void addOverviewmapzoomMenuItem(JMenu displayMenu, final float scale) {
        String menuItemName = "Set overview map scale=" + scale;
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                overviewMap.setup(new BlankMapRenderer(scale));
            }
        });
        displayMenu.add(menuItem);
    }

    private void addMainmapzoomMenuItem(JMenu displayMenu, final float scale) {
        String menuItemName = "Set main map scale=" + scale;
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rectangle visRect = mainMap.getVisibleRect();

                int oldWidth = mainMap.getWidth();
                mainMap.setup(new BlankMapRenderer(scale));

                int newWidth = mainMap.getPreferredSize().width;

                int oldCenterX = visRect.x + (visRect.width / 2);
                int newCenterX = oldCenterX * newWidth / oldWidth;
                visRect.x = newCenterX - visRect.width / 2;

                int oldCenterY = visRect.y + (visRect.height / 2);
                int newCenterY = oldCenterY * newWidth / oldWidth;
                visRect.y = newCenterY - visRect.height / 2;

                /*
                 * LL: I'm not sure why the 'if' is necessary in the following,
                 * but the view does not center on the right spot without it.
                 */
                if (oldWidth < newWidth) {
                    mainMap.setSize(mainMap.getPreferredSize());
                    mainMap.scrollRectToVisible(visRect);
                } else {
                    mainMap.scrollRectToVisible(visRect);
                    mainMap.setSize(mainMap.getPreferredSize());
                }
            }
        });
        displayMenu.add(menuItem);
    }

    /**
     *
     * @return
     */
    public JScrollPane createMainMap() {
        if (null == this.mainMap) {
            // this.mainMap = new MapJPanel();
            this.mainMap = new MapViewJComponentConcrete();
            mainMapScrollPane1 = new JScrollPane();
            mainMapScrollPane1.setViewportView(this.mainMap);
            addMainMapAndOverviewMapMediatorIfNecessary();
        }

        return mainMapScrollPane1;
    }

    /**
     *
     * @return
     */
    public JPanel createOverviewMap() {
        if (null == this.overviewMap) {
            // this.overviewMap = new OverviewMapJPanel();
            this.overviewMap = new OverviewMapJComponent(r);
            this.overviewMap.setup(new BlankMapRenderer(0.4F));
            addMainMapAndOverviewMapMediatorIfNecessary();
        }

        return overviewMap;
        // return new TestPanel();
    }

    private void addMainMapAndOverviewMapMediatorIfNecessary() {
        if (this.mainMap != null && this.overviewMap != null
                && null == this.mediator) {
            // Rectangle r = this.overviewMap.getMainMapVisibleRect();
            this.mediator = new MainMapAndOverviewMapMediator(overviewMap,
                    mainMapScrollPane1.getViewport(), mainMap, r);
        }
    }

    /**
     *
     * @return
     */
    public JLabel createCashJLabel() {
        return null;
    }

    /**
     *
     * @return
     */
    public JLabel createDateJLabel() {
        return null;
    }

    /**
     *
     * @return
     */
    public JMenu createHelpMenu() {
        return null;
    }

    /**
     *
     * @return
     */
    public JTabbedPane createTrainsJTabPane() {
        return null;
    }

    /**
     *
     * @return
     */
    public JMenu createReportsMenu() {
        // TODO Auto-generated method stub
        return null;
    }
}