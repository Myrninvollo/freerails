package jfreerails.client;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;

public interface GUIComponentFactory {
	
	JComponent createOverviewMap();
	
	JComponent createMainMap();
	
	JLabel createMessagePanel();
	
	JMenu createBuildMenu();
	
	JMenu createGameMenu();
	
	JMenu createDisplayMenu();
	
}
