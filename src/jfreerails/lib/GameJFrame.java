package jfreerails.lib;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 *
 * @author  Luke Lindsay
 */
public class GameJFrame extends javax.swing.JFrame {

	Painter renderer;

	JComponent jComponent;
    
    GameModel model = GameModel.NULL_MODEL;
    
    boolean fullscreen=false;
    
    public void setFullScreen(boolean b){
        this.fullscreen=b;
    }

	public GameJFrame(Painter r) {
		renderer = r;
		jComponent = new JComponent() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				renderer.paint((Graphics2D) g);
			}

		};
		initComponents();
	}

	public GameJFrame(JComponent comp) {
		jComponent = comp;
		initComponents();
	}

	private void initComponents() {
		getContentPane().add(jComponent);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});
	
	}

	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}
    
    public void setGameModel(GameModel gm){
        this.model=gm;
    }

	public void startGameLoop() {
		ScreenHandler screenHandler = new ScreenHandler(this, fullscreen);
		GameLoop gameLoop = new GameLoop(screenHandler, this.model);
		Thread t = new Thread(gameLoop);
		t.start();
	}

}
