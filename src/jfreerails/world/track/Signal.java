package jfreerails.world.track;

final public class Signal {
    private final SignalValue signalValue;

    public SignalValue getSignalValue() {
        return signalValue;
    }

    public Signal(SignalValue v) {
        signalValue = v;
    }
}