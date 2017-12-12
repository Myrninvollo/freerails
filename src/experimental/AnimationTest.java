package experimental;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import jfreerails.client.GameLoop;
import jfreerails.client.ScreenHandler;

public class AnimationTest extends JComponent {
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long l = System.currentTimeMillis();
        String str=String.valueOf(l);
        g.drawString(str, 100,100);
        
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.getContentPane().add(new AnimationTest());
        boolean fullscreen=false;
        ScreenHandler screenHandler=new ScreenHandler(f, fullscreen);
        GameLoop gameLoop=new GameLoop(screenHandler);
        Thread t=new Thread(gameLoop);
        t.start();
    }
    
}
