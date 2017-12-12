/*
 * TrackView.java
 *
 * Created on 23 September 2002, 20:09
 */

package experimental;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import jfreerails.controller.*;
import jfreerails.lib.GameModel;
import jfreerails.move.ChangeTrainPositionMove;
import jfreerails.world.misc.FreerailsPathIterator;
import jfreerails.world.misc.FreerailsPathIteratorImpl;
import jfreerails.world.misc.IntLine;
import jfreerails.world.train.EngineModel;
import jfreerails.world.train.PathWalker;
import jfreerails.world.train.PathWalkerImpl;
import jfreerails.world.train.TrainList;
import jfreerails.world.train.TrainModel;
/**
 *
 * @author  Luke Lindsay
 */
public class TrainDemo extends javax.swing.JPanel implements GameModel {

	//TrainView trainView = new TrainView();

	TrainPainter trainPainter = new TrainPainter();

	int distanceTravelled = 0;

	int deltaDistance = 0;

	long lastTime = System.currentTimeMillis();

	final ArrayList points;

	final TrainMover trainMover;

	final TrainList trainList;

	/** Creates new form TrackView */
	public TrainDemo() {

		TrainFixture trainFixture = new TrainFixture();

		this.points = trainFixture.getPoints();

		this.trainMover = trainFixture.getTrainMover();

		this.trainList = trainFixture.getTrainList();

		initComponents();
	}

	public void update() {

		long now = System.currentTimeMillis();
		long deltaTime = now - lastTime;
		deltaDistance = (int) (0.15 * deltaTime);
		//Move at 150 pixels per second.
		distanceTravelled += deltaDistance;
		lastTime = now;

		ChangeTrainPositionMove m = null;
		TrainModel train = trainList.getTrain(0);
		
		m = trainMover.update(deltaDistance);
		m.doMove(trainList);		
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(
			"Lay track by clicking the mouse on the area below!",
			10,
			15);
		FreerailsPathIterator it =
			FreerailsPathIteratorImpl.forwardsIterator(points);
		IntLine line = new IntLine();
		while (it.hasNext()) {
			it.nextSegment(line);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);
		}

		/*
		PathWalker pw = new PathWalkerImpl(pathIterator());
		
		pw.stepForward(distanceTravelled);
		double actualDistance = 0;
		while (pw.hasNext()) {
			pw.nextSegment(line);
			actualDistance += line.getLength();
		}
		if (actualDistance + 10 < distanceTravelled) {
			distanceTravelled = 0;
		}
		*/
		TrainModel train = trainList.getTrain(0);

		PathWalker pw = new PathWalkerImpl(train.getPosition().path());
		trainPainter.paintTrain(g, pw);
	}

	private void initComponents() {
		setLayout(null);

		addMouseListener(new java.awt.event.MouseAdapter() {

			public void mousePressed(java.awt.event.MouseEvent evt) {
				formMouseClicked(evt);
			}
		});

	}

	private void formMouseClicked(java.awt.event.MouseEvent evt) {
		// Add your handling code here:
		Point p = new Point(evt.getX(), evt.getY());
		points.add(p);
		this.repaint();

	}

}
