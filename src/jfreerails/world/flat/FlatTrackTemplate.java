
package jfreerails.world.flat;

import jfreerails.lib.FreerailsSerializable;

public interface FlatTrackTemplate extends FreerailsSerializable {
    FlatTrackTemplate getRotatedInstance(Rotation r);
    boolean contains(FlatTrackTemplate ftt);
    int getTemplate();
}
