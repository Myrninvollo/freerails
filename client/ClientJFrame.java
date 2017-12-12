package jfreerails.client;

import jfreerails.client.view.map.MapViewJComponentContainer;
//import scrollingdemo.ScrollingDemoJFrame;

/*
 * TestJFrame.java
 *
 * Created on 16 December 2001, 22:33
 */

/** This class is only intended to be used to demonstrate
 * the scrolling wrapped map components.
 *
 * @author Luke Lindsay
 */
public class ClientJFrame
	extends javax.swing.JFrame
	implements jfreerails.client.TextMessenger {


//	public ClientJFrame() {
//
//		mainMapViewContainer =
//			MapViewJComponentContainer.generateMainmapComponent(
//				new ScrollingDemoMapView(new ScrollingDemoNewMapView(5)));
//
//		overviewMapViewContainer =
//			MapViewJComponentContainer.generateOverviewMapComponent(
//				new ScrollingDemoNewMapView(1),
//				mainMapViewContainer);
//
//		initComponents();
//	}

	public ClientJFrame(
		MapViewJComponentContainer mainMap,
		MapViewJComponentContainer overviewMap, BuildMenu buildMenu) {
		this.mainMapViewContainer = mainMap;
		this.overviewMapViewContainer = overviewMap;
		
        this.buildMenu = buildMenu;
         jMenuBar1.add(buildMenu);
         setJMenuBar(jMenuBar1);
		initComponents();
	}

	private void initComponents() {
		textMessage = new javax.swing.JLabel("Message");
		
		jfreerails.lib.TextMessageHandler.setMessengerBoy(this);
       
		getContentPane().setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridBagConstraints3,
			gridBagConstraints2,
			gridBagConstraints1;

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		gridBagConstraints1 = new java.awt.GridBagConstraints();
		gridBagConstraints1.gridheight = 1;
		gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints1.weightx = 1.0;
		gridBagConstraints1.weighty = 1.0;
		getContentPane().add(mainMapViewContainer, gridBagConstraints1);

		gridBagConstraints3 = new java.awt.GridBagConstraints();
		gridBagConstraints3.gridheight = 1;
		gridBagConstraints3.gridy = 1;
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.weightx = 1.0;
		gridBagConstraints3.weighty = 0;
		getContentPane().add(textMessage, gridBagConstraints3);

		overviewMapViewContainer.setMinimumSize(new java.awt.Dimension(150, 150));
		gridBagConstraints2 = new java.awt.GridBagConstraints();
		gridBagConstraints2.gridx = 1;
		gridBagConstraints2.gridy = 0;
		gridBagConstraints2.anchor = java.awt.GridBagConstraints.NORTH;
		getContentPane().add(overviewMapViewContainer, gridBagConstraints2);
		this.setSize(600, 400);

	}

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}

	/** Runs the demo
	     * @param args the command line arguments
	     */
//	public static void main(String args[]) {
//		new ScrollingDemoJFrame().show();
//	}
	public void displayMessage(java.lang.String message) {
		textMessage.setText(message);
	}

	private jfreerails.client.view.map.MapViewJComponentContainer mainMapViewContainer;
	private javax.swing.JPanel blankPanel;
	private jfreerails.client.view.map.MapViewJComponentContainer overviewMapViewContainer;
	private javax.swing.JLabel textMessage;
	protected javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();

	protected BuildMenu buildMenu;

}