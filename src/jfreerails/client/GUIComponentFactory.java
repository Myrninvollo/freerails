package jfreerails.client;

import javax.swing.JComponent;
import javax.swing.JMenu;

public interface GUIComponentFactory {
	
	JComponent createOverviewMap();
	
	JComponent createMainMap();
	
	JComponent createMessagePanel();
	
	JMenu createBuildMenu();
	
	JMenu createGameMenu();
	
}
