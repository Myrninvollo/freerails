package jfreerails.world.top;

import jfreerails.world.common.GameCalendar;


final public class GameTimePeriod {
    private final GameCalendar start;
    private final GameCalendar end;

    public GameCalendar getStart() {
        return start;
    }

    public GameCalendar getEnd() {
        return end;
    }

    public GameTimePeriod(GameCalendar from, GameCalendar to) {
        start = from;
        end = to;
    }
}