/*
 * FreeRails
 * Copyright (C) 2000-2018 The FreeRails Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package freerails.model.track;

import freerails.model.terrain.TileTransition;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of this class represents one of the possible track configurations
 * in a map square - the combinations of directions in which track can be laid.
 * Instances of this class cannot be created and must be obtained via the static
 * methods herein.
 */
public class TrackConfiguration implements TrackConfigurations {

    private static final long serialVersionUID = 3618695301330974512L;
    private static final List<TrackConfiguration> flatTrackConfigurations;

    static {
        List<TrackConfiguration> configurations = new ArrayList<>(512);

        for (int i = 0; i < 512; i++) {
            configurations.add(i, new TrackConfiguration(i));
        }

        flatTrackConfigurations = configurations;
    }

    private final int length;
    private final int configuration;

    private TrackConfiguration(int configuration) {
        this.configuration = configuration;

        // Calculate length.
        int tempLength = 0;
        TileTransition[] vectors = TileTransition.getTransitions();

        for (TileTransition vector : vectors) {
            if (contains(vector.get9bitTemplate())) {
                tempLength += vector.getLength();
            }
        }

        length = tempLength;
    }

    /**
     * @return the superposition of two track templates
     */
    public static TrackConfiguration add(TrackConfigurations c, TrackConfigurations v) {
        int newTemplate = c.get9bitTemplate() | v.get9bitTemplate();

        return from9bitTemplate(newTemplate);
    }

    /**
     * @param i
     * @return
     */
    public static TrackConfiguration from9bitTemplate(int i) {
        return flatTrackConfigurations.get(i);
    }

    /**
     * @param v
     * @return
     */
    public static TrackConfiguration getFlatInstance(TrackConfigurations v) {
        return from9bitTemplate(v.get9bitTemplate());
    }

    /**
     * @param trackTemplate
     * @return
     */
    public static TrackConfiguration getFlatInstance(String trackTemplate) {
        String templateString = trackTemplate;
        // Hack - so that result is as expected by earlier written code.
        StringBuffer strb = new StringBuffer(templateString);
        strb = strb.reverse();
        templateString = strb.toString();

        // End of hack
        int i = Integer.parseInt(templateString, 2);

        return flatTrackConfigurations.get(i);
    }

    /**
     * @return the TrackConfiguration representing the track section c minus the
     * track sections represented by v.
     */
    public static TrackConfiguration subtract(TrackConfigurations c, TrackConfigurations v) {
        int newTemplate = c.get9bitTemplate() & (~v.get9bitTemplate());
        return from9bitTemplate(newTemplate);
    }

    public boolean contains(TrackConfigurations ftt) {
        int trackTemplate = ftt.get9bitTemplate();

        return contains(trackTemplate);
    }

    /**
     * @param trackTemplate
     * @return
     */
    public boolean contains(int trackTemplate) {
        return (trackTemplate | configuration) == configuration;
    }

    /**
     * @return
     */
    public int get8bitTemplate() {
        int newTemplate = 0;
        TileTransition[] vectors = TileTransition.getTransitions();

        for (TileTransition vector : vectors) {
            if (contains(vector)) {
                newTemplate = newTemplate | vector.get8bitTemplate();
            }
        }

        return newTemplate;
    }

    /**
     * @return an int representing this track configuration.
     */
    public int get9bitTemplate() {
        return configuration;
    }

    /**
     * Returns the length of track used in this configuration. Used to calculate
     * the cost of building track.
     */
    public int getLength() {
        return length;
    }

    /**
     * @return
     */
    public int getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final TrackConfiguration that = (TrackConfiguration) obj;

        return configuration == that.configuration;
    }

    @Override
    public int hashCode() {
        return configuration;
    }

    private Object readResolve() {
        return TrackConfiguration.from9bitTemplate(configuration);
    }

    /**
     * Returns a String representing this configuration, for example "north,
     * south".
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int matches = 0;

        if (contains(TrackConfiguration.getFlatInstance("000010000"))) {
            sb.append("tile center");
        } else {
            sb.append("no tile center");
        }

        for (int i = 0; i < 8; i++) {
            TileTransition v = TileTransition.getInstance(i);

            if (contains(v)) {
                sb.append(',');
                sb.append(v);
                matches++;
            }
        }

        return sb.toString().trim();
    }
}