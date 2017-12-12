package experimental;

import java.awt.Point;
import java.util.ArrayList;

import jfreerails.controller.TrainMover;
import jfreerails.world.misc.FreerailsPathIterator;
import jfreerails.world.train.EngineModel;
import jfreerails.world.train.TrainList;
import jfreerails.world.train.TrainModel;

/**
 * @author Luke Lindsay 30-Oct-2002
 *
 */
public class TrainFixture {

	TrainMover trainMover;

	ArrayList points = new ArrayList();

	TrainList trainList = new TrainList();

	public TrainFixture() {

		points.add(new Point(0, 0));
		points.add(new Point(80, 80));
		points.add(new Point(150, 100));

		TrainModel train = new TrainModel(new EngineModel());

		trainList.addTrain(train);

		if (null == trainList.getTrain(0)) {
			throw new NullPointerException();
		}

		FreerailsPathIterator from = pathIterator();
		FreerailsPathIterator to = pathIterator();
		trainMover = new TrainMover(from, to, trainList, 0);
	}

	public FreerailsPathIterator pathIterator() {
		return new ToAndFroPathIterator(points);
	}
	/**
	 * Returns the points.
	 * @return ArrayList
	 */
	public ArrayList getPoints() {
		return points;
	}

	/**
	 * Returns the trainList.
	 * @return TrainList
	 */
	public TrainList getTrainList() {
		return trainList;
	}

	/**
	 * Returns the trainMover.
	 * @return TrainMover
	 */
	public TrainMover getTrainMover() {
		return trainMover;
	}

}
