package experimental;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JFrame;
import jfreerails.client.train.TrainTypeView;
import jfreerails.client.train.TrainView;
import jfreerails.client.train.ViewPerspective;
import jfreerails.world.flat.OneTileMoveVector;

public class DisplayTrainViews extends JComponent {

	TrainView trainView = new TrainView();

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		

		trainView.setViewPerspective(ViewPerspective.OVERHEAD);

		Iterator trainTypes = TrainTypeView.iterator();
		int row = 2;
		int column = 2;
		while (trainTypes.hasNext()) {
			column++;
			row = 2;
			trainView.setTrainTypes((TrainTypeView) trainTypes.next());

			OneTileMoveVector[] vectors = OneTileMoveVector.getList();

			for (int i = 0; i < vectors.length; i++) {
				row++;
				trainView.setDirection(vectors[i]);

				Point p = new Point(column * 50 + 15, row * 50 + 15);

				trainView.drawTrain(g, p);
				g.setColor(Color.BLUE);
				g.drawLine(p.x, p.y, p.x + 1, p.y + 1);
			}
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().add(new DisplayTrainViews());
		f.show();
		f.setSize(500, 500);
	}

}
