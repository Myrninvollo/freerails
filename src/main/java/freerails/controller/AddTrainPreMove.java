/*
 * Created on 18-Feb-2005
 *
 */
package freerails.controller;

import freerails.move.*;
import freerails.world.accounts.AddItemTransaction;
import freerails.world.accounts.Transaction;
import freerails.world.cargo.ImmutableCargoBundle;
import freerails.world.common.*;
import freerails.world.player.FreerailsPrincipal;
import freerails.world.top.KEY;
import freerails.world.top.ReadOnlyWorld;
import freerails.world.top.SKEY;
import freerails.world.train.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luke
 */
public class AddTrainPreMove implements PreMove {

    private static final long serialVersionUID = 4050201951105069624L;

    private final int engineTypeId;

    private final ImInts wagons;
    private final ImPoint point;
    private final FreerailsPrincipal principal;
    private final ImmutableSchedule schedule;

    public AddTrainPreMove(int e, ImInts wags, ImPoint p,
                           FreerailsPrincipal fp, ImmutableSchedule s) {
        engineTypeId = e;
        wagons = wags;
        point = p;
        principal = fp;
        schedule = s;
        if (null == wags)
            throw new NullPointerException();
        if (null == p)
            throw new NullPointerException();
        if (null == fp)
            throw new NullPointerException();
        if (null == s)
            throw new NullPointerException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AddTrainPreMove))
            return false;

        final AddTrainPreMove addTrainPreMove = (AddTrainPreMove) o;

        if (engineTypeId != addTrainPreMove.engineTypeId)
            return false;
        if (!point.equals(addTrainPreMove.point))
            return false;
        if (!principal.equals(addTrainPreMove.principal))
            return false;
        if (!schedule.equals(addTrainPreMove.schedule))
            return false;
        return wagons.equals(addTrainPreMove.wagons);
    }

    @Override
    public int hashCode() {
        int result;
        result = engineTypeId;
        result = 29 * result + point.hashCode();
        result = 29 * result + principal.hashCode();
        result = 29 * result + schedule.hashCode();
        return result;
    }

    PathOnTiles initPositionStep1(ReadOnlyWorld w) {
        PositionOnTrack[] pp = FlatTrackExplorer.getPossiblePositions(w, point);
        FlatTrackExplorer fte;
        try {
            fte = new FlatTrackExplorer(w, pp[0]);
        } catch (NoTrackException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        List<Step> steps = new ArrayList<>();
        int length = calTrainLength();
        int distanceTravelled = 0;
        PositionOnTrack p = new PositionOnTrack();
        while (distanceTravelled < length) {
            fte.nextEdge();
            fte.moveForward();
            p.setValuesFromInt(fte.getPosition());
            Step v = p.cameFrom();
            distanceTravelled += v.getLength();
            steps.add(v);

        }
        return new PathOnTiles(point, steps);
    }

    private int calTrainLength() {
        TrainModel train = new TrainModel(engineTypeId, wagons, 0);
        return train.getLength();
    }

    TrainMotion initPositionStep2(PathOnTiles path) {
        // TODO fix code.
        return new TrainMotion(path, path.steps(), calTrainLength(),
                ConstAcc.STOPPED);
    }

    /**
     * Generates a move that does the following.
     * <ol>
     * <li>Adds the train</li>
     * <li>Adds a cargo bundle to represent the cargo the train is carrying</li>
     * <li>Adds a schedule for the train</li>
     * <li>Adds transaction to pay for the train</li>
     * <li>Init. the trains position and motion</li>
     * </ol>
     */
    public Move generateMove(ReadOnlyWorld w) {
        // Add cargo bundle.
        int bundleId = w.size(principal, KEY.CARGO_BUNDLES);
        ImmutableCargoBundle cargo = ImmutableCargoBundle.EMPTY_BUNDLE;
        AddItemToListMove addCargoBundle = new AddItemToListMove(
                KEY.CARGO_BUNDLES, bundleId, cargo, principal);

        // Add schedule
        int scheduleId = w.size(principal, KEY.TRAIN_SCHEDULES);
        AddItemToListMove addSchedule = new AddItemToListMove(
                KEY.TRAIN_SCHEDULES, scheduleId, schedule, principal);

        // Add train to train list.
        TrainModel train = new TrainModel(engineTypeId, wagons, scheduleId,
                bundleId);
        int trainId = w.size(principal, KEY.TRAINS);
        AddItemToListMove addTrain = new AddItemToListMove(KEY.TRAINS, trainId,
                train, principal);

        // Pay for train.
        int quantity = 1;
        /* Determine the price of the train. */
        EngineType engineType = (EngineType) w.get(SKEY.ENGINE_TYPES,
                engineTypeId);
        Money price = engineType.getPrice();
        Transaction transaction = new AddItemTransaction(
                Transaction.Category.TRAIN, engineTypeId, quantity, new Money(
                -price.getAmount()));
        AddTransactionMove transactionMove = new AddTransactionMove(principal,
                transaction);

        // Setup and add train position.

        PathOnTiles path = initPositionStep1(w);
        TrainMotion motion = initPositionStep2(path);

        Move addPosition = new AddActiveEntityMove(motion, trainId, principal);

        return new CompositeMove(addCargoBundle, addSchedule, addTrain,
                transactionMove, addPosition);
    }

}
