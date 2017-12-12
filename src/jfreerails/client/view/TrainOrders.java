/*
 * TrainOrders.java
 *
 * Created on 30 March 2003, 02:00
 */

package jfreerails.client.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import jfreerails.client.renderer.ViewLists;
import jfreerails.world.cargo.CargoType;
import jfreerails.world.station.StationModel;
import jfreerails.world.top.KEY;
import jfreerails.world.top.NonNullElements;
import jfreerails.world.top.World;
import jfreerails.world.train.TrainOrdersModel;

/**
 *
 * @author  Luke
 *
 * Represents a single entry in the TrainScheduleJPanel
 */
public class TrainOrders extends javax.swing.JPanel {

	private int[] consist = new int[0];

	private java.awt.Color oldBackground;

	private int field1;

	private World w;

	private ViewLists vl;

	int orderNo = 0;

	private int stationNumber = TrainOrdersModel.NO_STATION;

	private TrainScheduleJPanel parentSchedule;

	private TrainOrdersModel trainOrdersModel;

	private boolean waitUntilFull;

	public String toString() {
	    return "stationNumber = " + stationNumber + ", orderNo = " +
	    orderNo; 
	}

	/**
	 * Creates new form TrainOrders, with no set order
	 */
	public TrainOrders(TrainScheduleJPanel parent) {
	    this(parent, null);
	}
	
	/**
	 * Creates new form TrainOrders
	 * @param Model the TrainOrdersModel that this object represents 
	 */
	public TrainOrders(TrainScheduleJPanel parent, TrainOrdersModel model) {
	    trainOrdersModel = model;
	    parentSchedule = parent;
	    if (trainOrdersModel != null) {
		stationNumber = trainOrdersModel.getStationNumber();
	    }
		initComponents();
	}

	public void setup(World world, ViewLists vl) {
		w = world;
		this.vl = vl;
		setupStationsInPopup();
		setupWagonsPopup();
		sideOnTrainViewJPanel1.setup(w, vl, null);
	}

	public void display(int orderNo) {
		this.orderNo = orderNo;

		if (orderNo == 0) {
		    orderNumber.setText("P."); //order 0 is priority orders.
		} else {
		    orderNumber.setText(String.valueOf(orderNo) + ".");
		}

		this.setGotoStation(false);

		this.sideOnTrainViewJPanel1.removeAllWagons();

		if (trainOrdersModel == null ||
			trainOrdersModel.isNoConsistChange()) {
			this.sideOnTrainViewJPanel1.setNoChange(true);
		} else {
			this.sideOnTrainViewJPanel1.setNoChange(false);
			int[] consist = trainOrdersModel.getConsist();
			for (int i = 0; i < consist.length; i++) {
				this.sideOnTrainViewJPanel1.addWagon(consist[i]);
			}
		}
		if (trainOrdersModel != null) {
		StationModel station =
			(StationModel) w.get(KEY.STATIONS, trainOrdersModel.getStationNumber());
		this.stationName.setText(station.getStationName());
		    setupWagonsPopup();
		    setWaitUntilFull(trainOrdersModel.getWaitUntilFull());
		} else {
		    stationName.setText("No station selected");
		    setWaitUntilFull(false);
		}

		setupStationsInPopup();
	}

	/** This sets the list of stations in the select station popup.
	 *It needs to be called before each time the popup is shown since
	 *stations may have been added or removed.
	 */
	private void setupStationsInPopup() {

		changeStation.removeAll(); //Remove existing menu items.
		NonNullElements stations = new NonNullElements(KEY.STATIONS, w);
		while (stations.next()) {
			final StationModel stationModel =
				(StationModel) stations.getElement();
			final int stationIndex = stations.getIndex();
			JMenuItem stationMenuItem = new JMenuItem();

			stationMenuItem.setText(stationModel.getStationName());
			stationMenuItem
				.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					System.out.println(
						"Station set to " + stationModel.getStationName());
					setStationNameText(stationModel.getStationName());
					setStationNumber(stationIndex);
				}
			});
			changeStation.add(stationMenuItem);

		}

		JSeparator jSeparator1 = new javax.swing.JSeparator();
		changeStation.add(jSeparator1);

		JMenuItem removeStation = new JMenuItem();
		removeStation.setText("Remove Station");
		removeStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            if (parentSchedule.stations.size() > 1) {
                                // don't allow last station to be removed whilst we are unable to support this.
                                parentSchedule.removeStation(TrainOrders.this);
                                parentSchedule = null;
                            }
			}});
		changeStation.add(removeStation);

		JMenuItem addStation = new JMenuItem();
		addStation.setText("Add Station");
		addStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			TrainOrders o = new TrainOrders (parentSchedule,
			getNewOrders());
			o.setup(w, vl);
			parentSchedule.addStation(orderNo, o);
			}
			});
		changeStation.add(addStation);
	}

	private void setupWagonsPopup() {
		changeConsist.removeAll(); //Remove existing menu items.

		NonNullElements cargoTypes = new NonNullElements(KEY.CARGO_TYPES, w);
		while (cargoTypes.next()) {
			final CargoType wagonType = (CargoType) cargoTypes.getElement();
			JMenuItem wagonMenuItem = new JMenuItem();
			final int wagonTypeNumber = cargoTypes.getIndex();
			wagonMenuItem.setText(wagonType.getDisplayName());
			wagonMenuItem
				.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {

					addWagon(wagonTypeNumber);
				}
			});
			changeConsist.add(wagonMenuItem);
		}
		JSeparator jSeparator1 = new javax.swing.JSeparator();
		changeConsist.add(jSeparator1);
		JMenuItem removeAll = new JMenuItem();
		removeAll.setText("Remove All");
		removeAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("Remove all wagons");
				removeAllWagons();
			}
		});
		changeConsist.add(removeAll);

		//Add 'No change' menu item.
		changeConsist.add(new JSeparator());
		JMenuItem noChangeMenuItem = new JMenuItem();
		noChangeMenuItem.setText("No change");
		noChangeMenuItem
			.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("No Change");
				noChange();
			}
		});
		changeConsist.add(noChangeMenuItem);

	}

	private void setStationNameText(String s) {
		this.stationName.setText(s);
	}

	private void addWagon(int i) {
		this.sideOnTrainViewJPanel1.setNoChange(false);
		this.sideOnTrainViewJPanel1.addWagon(i);
		this.repaint();
	}

	private void removeAllWagons() {
		this.sideOnTrainViewJPanel1.setNoChange(false);
		this.sideOnTrainViewJPanel1.removeAllWagons();
		this.repaint();
	}

	private void noChange() {
		this.sideOnTrainViewJPanel1.removeAllWagons();
		this.sideOnTrainViewJPanel1.setNoChange(true);
		this.repaint();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() { //GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

		changeWaitUntilFullOrders = new javax.swing.JPopupMenu();
		wait = new javax.swing.JMenuItem();
		dontWait = new javax.swing.JMenuItem();
		changeStation = new javax.swing.JPopupMenu();
		changeConsist = new javax.swing.JPopupMenu();
		orderNumber = new javax.swing.JLabel();
		waitUntilFullOrders = new javax.swing.JLabel();
        stationName = new javax.swing.JTextArea();
        sideOnTrainViewJPanel1 = new jfreerails.client.view.SideOnTrainViewJPanel();

		wait.setText("Wait Unitl Full");
		wait.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				waitActionPerformed(evt);
			}
		});

		changeWaitUntilFullOrders.add(wait);

		dontWait.setText("Don't Wait");
		dontWait.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dontWaitActionPerformed(evt);
			}
		});

		changeWaitUntilFullOrders.add(dontWait);

        setLayout(new java.awt.GridBagLayout());

		addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				formFocusGained(evt);
			}
			public void focusLost(java.awt.event.FocusEvent evt) {
				formFocusLost(evt);
			}
		});
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				formKeyPressed(evt);
			}
		});
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				formMouseEntered(evt);
			}
		});

		orderNumber.setText("1.");
		orderNumber.setPreferredSize(new java.awt.Dimension(16, 16));
        add(orderNumber, new java.awt.GridBagConstraints());

		waitUntilFullOrders.setText("W");
		waitUntilFullOrders.setToolTipText("Wait Until Full");
		waitUntilFullOrders.setPreferredSize(new java.awt.Dimension(16, 16));
        waitUntilFullOrders.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				waitUntilFullOrdersMouseClicked(evt);
			}
		});

        add(waitUntilFullOrders, new java.awt.GridBagConstraints());

        stationName.setEditable(false);
        stationName.setLineWrap(true);
        stationName.setRows(2);
		stationName.setText("Bristol Temple Meades");
        stationName.setWrapStyleWord(true);
        stationName.setMaximumSize(new java.awt.Dimension(100, 2147483647));
		stationName.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				stationNameMouseClicked(evt);
			}
		});

        add(stationName, new java.awt.GridBagConstraints());

        sideOnTrainViewJPanel1.setMinimumSize(new java.awt.Dimension(100, 34));
        sideOnTrainViewJPanel1.setPreferredSize(new java.awt.Dimension(100, 34));
        sideOnTrainViewJPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				sideOnTrainViewJPanel1MouseClicked(evt);
			}
		});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(sideOnTrainViewJPanel1, gridBagConstraints);

	} //GEN-END:initComponents

	private void sideOnTrainViewJPanel1MouseClicked(
		java.awt.event.MouseEvent evt) {
		//GEN-FIRST:event_sideOnTrainViewJPanel1MouseClicked
		// Add your handling code here:
		setupStationsInPopup();
		this.changeConsist.show(evt.getComponent(), evt.getX(), evt.getY());

	} //GEN-LAST:event_sideOnTrainViewJPanel1MouseClicked

	private void formKeyPressed(
		java.awt.event.KeyEvent evt) { //GEN-FIRST:event_formKeyPressed
		// Add your handling code here:
		switch (evt.getKeyCode()) {
			case KeyEvent.VK_O :
				this.changeWaitUntilFullOrders.show(
					this.waitUntilFullOrders,
					this.waitUntilFullOrders.getX(),
					this.waitUntilFullOrders.getY());
				break;
			case KeyEvent.VK_C :
				this.changeConsist.show(
					this.sideOnTrainViewJPanel1,
					0,
					this.sideOnTrainViewJPanel1.getY());
				break;
			case KeyEvent.VK_S :
				this.changeStation.show(
					this.stationName,
					this.stationName.getX(),
					this.stationName.getY());
				break;
			case KeyEvent.VK_UP :

				this.getParent().getParent().dispatchEvent(evt);
				break;
			case KeyEvent.VK_DOWN :

				this.getParent().getParent().dispatchEvent(evt);
				break;
		}

	} //GEN-LAST:event_formKeyPressed

	private void formMouseEntered(
		java.awt.event.MouseEvent evt) { //GEN-FIRST:event_formMouseEntered
		// Add your handling code here:
		this.requestFocus();
	} //GEN-LAST:event_formMouseEntered

	private void formFocusLost(
		java.awt.event.FocusEvent evt) { //GEN-FIRST:event_formFocusLost
		// Add your handling code here:
		this.setBackground(this.oldBackground);
	} //GEN-LAST:event_formFocusLost

	private void formFocusGained(
		java.awt.event.FocusEvent evt) { //GEN-FIRST:event_formFocusGained
		// Add your handling code here:
		this.oldBackground = this.getBackground();
		this.setBackground(java.awt.Color.yellow);
	} //GEN-LAST:event_formFocusGained

	private void dontWaitActionPerformed(java.awt.event.ActionEvent evt) {
		//GEN-FIRST:event_dontWaitActionPerformed
		// Add your handling code here:
		setWaitUntilFull(false);
	} //GEN-LAST:event_dontWaitActionPerformed

	private void waitActionPerformed(
		java.awt.event.ActionEvent evt) { //GEN-FIRST:event_waitActionPerformed
		// Add your handling code here:
		setWaitUntilFull(true);
	} //GEN-LAST:event_waitActionPerformed

	private void stationNameMouseClicked(java.awt.event.MouseEvent evt) {
		//GEN-FIRST:event_stationNameMouseClicked
		// Add your handling code here:
		System.out.println("changeStation");
		this.changeStation.show(evt.getComponent(), evt.getX(), evt.getY());
	} //GEN-LAST:event_stationNameMouseClicked

	private void waitUntilFullOrdersMouseClicked(
		java.awt.event.MouseEvent evt) {
		//GEN-FIRST:event_waitUntilFullOrdersMouseClicked
		// Add your handling code here:
		this.changeWaitUntilFullOrders.show(
			evt.getComponent(),
			evt.getX(),
			evt.getY());
	} //GEN-LAST:event_waitUntilFullOrdersMouseClicked

	/**
	 * Generate a TrainOrdersModel from the state of this panel
	 */
	public TrainOrdersModel getNewOrders() {
		int[] wagons;

	    if (stationNumber == TrainOrdersModel.NO_STATION) {
		return null;
	    }

		if (this.sideOnTrainViewJPanel1.isNoChange()) {
			wagons = null; //null represents no change to the consist.
		} else {
			wagons = this.sideOnTrainViewJPanel1.getWagons();
		}
		TrainOrdersModel newOrders =
		new TrainOrdersModel(this.stationNumber, wagons, waitUntilFull);
		return newOrders;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	/** Set whether to indicate that the train is going to this station. */
	public void setGotoStation(boolean b) {
		java.awt.Color c;
		if (b) {
			c = new java.awt.Color(0, 0, 0);
		} else {
			c = new java.awt.Color(102, 102, 102);
		}
		this.stationName.setForeground(c);
		this.waitUntilFullOrders.setForeground(c);
		this.orderNumber.setForeground(c);
	}

	private void setWaitUntilFull(boolean yesNo) {
	    waitUntilFull = yesNo;
	    if (yesNo == true) {
		waitUntilFullOrders.setText("W");
	    } else {
		waitUntilFullOrders.setText("-");
	    }
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPopupMenu changeConsist;
	private javax.swing.JPopupMenu changeStation;
	private javax.swing.JPopupMenu changeWaitUntilFullOrders;
    private javax.swing.JMenuItem dontWait;
	private javax.swing.JLabel orderNumber;
    private jfreerails.client.view.SideOnTrainViewJPanel sideOnTrainViewJPanel1;
    private javax.swing.JTextArea stationName;
	private javax.swing.JMenuItem wait;
    private javax.swing.JLabel waitUntilFullOrders;
	// End of variables declaration//GEN-END:variables

}
