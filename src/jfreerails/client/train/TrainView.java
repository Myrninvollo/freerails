package jfreerails.client.train;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import jfreerails.world.flat.OneTileMoveVector;

public class TrainView {

	private GraphicsConfiguration defaultConfiguration =
		GraphicsEnvironment
			.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice()
			.getDefaultConfiguration();

	private ViewPerspective viewPerspective = ViewPerspective.OVERHEAD;

	private TrainTypeView trainTypes = TrainTypeView.PASSENGER;

	private SideOnViewSize sideOnViewSize = SideOnViewSize.TINY;

	private OneTileMoveVector direction = OneTileMoveVector.NORTH;

	private BufferedImage[][] trains;

	public void drawTrain(Graphics g, Point p) {

		int x = trainTypes.getTypeNumber();
		int y = direction.getNumber();
		g.drawImage(trains[x][y], p.x - 15, p.y - 15, null);
		//drawTrainWithoutBuffer(g, p);

	}

	private void drawTrainWithoutBuffer(Graphics g, Point p) {
		g.setColor(trainTypes.getColor());
		if (ViewPerspective.OVERHEAD == viewPerspective) {

			Graphics2D g2 = (Graphics2D) g.create();
			g2.rotate(direction.getDirection(), p.x, p.y);
			g2.fillRect(p.x - 5, p.y - 10, 10, 20);

		} else {

			int width = sideOnViewSize.getWidth();
			int height = sideOnViewSize.getHeight();
			g.fillRect(
				p.x + 25 - width / 2,
				p.y + 25 - width / 2,
				width,
				height);
		}

	}

	public OneTileMoveVector getDirection() {
		return direction;
	}

	public SideOnViewSize getSideOnViewSize() {
		return sideOnViewSize;
	}

	public TrainTypeView getTrainTypes() {
		return trainTypes;
	}

	public ViewPerspective getViewPerspective() {
		return viewPerspective;
	}

	public void setDirection(OneTileMoveVector direction) {
		this.direction = direction;
	}

	public void setSideOnViewSize(SideOnViewSize sideOnViewSize) {
		this.sideOnViewSize = sideOnViewSize;
	}

	public void setTrainTypes(TrainTypeView trainTypes) {
		this.trainTypes = trainTypes;
	}

	public void setViewPerspective(ViewPerspective viewPerspective) {
		this.viewPerspective = viewPerspective;
	}

	private void setup() {
		Object o = TrainTypeView.BULK_FREIGHT;
		int numberOfTrinTypes = TrainTypeView.getSize();

		trains = new BufferedImage[numberOfTrinTypes][8];

		int row = 0;
		int column = 0;
		Iterator trainTypes = TrainTypeView.iterator();
		while (trainTypes.hasNext()) {
			row = 0;
			this.setTrainTypes((TrainTypeView) trainTypes.next());
			OneTileMoveVector[] vectors = OneTileMoveVector.getList();
			for (int i = 0; i < vectors.length; i++) {
				trains[column][row] =
					defaultConfiguration.createCompatibleImage(
						30,
						30,
						Transparency.TRANSLUCENT);
				Graphics g = trains[column][row].getGraphics();
				row++;
				this.setDirection(vectors[i]);
				Point p = new Point(15, 15);
				this.drawTrainWithoutBuffer(g, p);
			}
			column++;
		}
	}
	public TrainView() {

		setup();
	}

}
