
package jfreerails.world.misc;

import jfreerails.world.common.FreerailsSerializable;


public interface FlatTrackTemplate extends FreerailsSerializable {
    FlatTrackTemplate getRotatedInstance(Rotation r);
    boolean contains(FlatTrackTemplate ftt);
    int getTemplate();
}
