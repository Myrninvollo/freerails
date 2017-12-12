package jfreerails.world.train;

final public class WagonModel {
    private WagonType type;

    public WagonModel(WagonType t) {
        type = t;
    }

    public WagonType getType() {
        return type;
    }
}