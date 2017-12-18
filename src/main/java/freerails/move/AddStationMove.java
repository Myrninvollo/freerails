/*
 * Created on 26-May-2003
 *
 */
package freerails.move;

import freerails.world.cargo.ImmutableCargoBundle;
import freerails.world.common.ImPoint;
import freerails.world.player.FreerailsPrincipal;
import freerails.world.station.StationModel;
import freerails.world.top.KEY;
import freerails.world.top.ReadOnlyWorld;
import freerails.world.top.SKEY;

/**
 * This {@link CompositeMove}adds a station to the station list and adds a
 * cargo bundle (to store the cargo waiting at the station) to the cargo bundle
 * list.
 *
 * @author Luke
 */
public class AddStationMove extends CompositeMove {
    private static final long serialVersionUID = 3256728398461089080L;

    private AddStationMove(Move[] moves) {
        super(moves);
    }

    public static AddStationMove generateMove(ReadOnlyWorld w,
                                              String stationName, ImPoint p,
                                              ChangeTrackPieceMove upgradeTrackMove, FreerailsPrincipal principal) {
        int cargoBundleNumber = w.size(principal, KEY.CARGO_BUNDLES);
        Move addCargoBundleMove = new AddCargoBundleMove(cargoBundleNumber,
                ImmutableCargoBundle.EMPTY_BUNDLE, principal);
        int stationNumber = w.size(principal, KEY.STATIONS);
        StationModel station = new StationModel(p.x, p.y, stationName, w
                .size(SKEY.CARGO_TYPES), cargoBundleNumber);

        Move addStation = new AddItemToListMove(KEY.STATIONS, stationNumber,
                station, principal);

        return new AddStationMove(new Move[]{upgradeTrackMove,
                addCargoBundleMove, addStation});
    }

    public static AddStationMove upgradeStation(
            ChangeTrackPieceMove upgradeTrackMove) {
        return new AddStationMove(new Move[]{upgradeTrackMove});
    }

    public StationModel getNewStation() {
        AddItemToListMove addStation = (AddItemToListMove) super.getMove(2);

        return (StationModel) addStation.getAfter();
    }
}