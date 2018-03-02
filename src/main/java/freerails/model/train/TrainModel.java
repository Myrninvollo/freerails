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

package freerails.model.train;

import freerails.util.ImmutableList;

import java.io.Serializable;

/**
 * Represents a train.
 */
public class TrainModel implements Serializable {

    public static final int WAGON_LENGTH = 24;
    public static final int MAX_NUMBER_OF_WAGONS = 6;
    public static final int MAX_TRAIN_LENGTH = (1 + MAX_NUMBER_OF_WAGONS) * WAGON_LENGTH;
    private static final long serialVersionUID = 3545235825756812339L;
    private final int scheduleId;
    private final int engineTypeId;
    private final ImmutableList<Integer> wagonTypes;
    private final int cargoBundleId;

    /**
     * @param engine
     * @param wagons
     * @param scheduleID
     * @param BundleId
     */
    public TrainModel(int engine, ImmutableList<Integer> wagons, int scheduleID, int BundleId) {
        engineTypeId = engine;
        wagonTypes = wagons;
        scheduleId = scheduleID;
        cargoBundleId = BundleId;
    }

    /**
     * @param wagons
     * @param BundleId
     */
    public TrainModel(ImmutableList<Integer> wagons, int BundleId) {
        wagonTypes = wagons;
        cargoBundleId = BundleId;
        engineTypeId = 0;
        scheduleId = 0;
    }

    /**
     * @param engine
     * @param wagons
     * @param scheduleID
     */
    public TrainModel(int engine, ImmutableList<Integer> wagons, int scheduleID) {
        engineTypeId = engine;
        wagonTypes = wagons;
        scheduleId = scheduleID;
        cargoBundleId = 0;
    }

    @Override
    public int hashCode() {
        int result;
        result = scheduleId;
        result = 29 * result + engineTypeId;
        result = 29 * result + cargoBundleId;

        return result;
    }

    /**
     * @param newEngine
     * @param newWagons
     * @return
     */
    public TrainModel getNewInstance(int newEngine, ImmutableList<Integer> newWagons) {
        return new TrainModel(newEngine, newWagons, scheduleId, cargoBundleId);
    }

    /**
     * @return
     */
    public int getLength() {
        return (1 + wagonTypes.size()) * WAGON_LENGTH; // Engine + wagons.
    }

    /**
     * @return
     */
    public int getNumberOfWagons() {
        return wagonTypes.size();
    }

    /**
     * @param i
     * @return
     */
    public int getWagon(int i) {
        return wagonTypes.get(i);
    }

    /**
     * @return
     */
    public int getEngineType() {
        return engineTypeId;
    }

    /**
     * @return
     */
    public int getCargoBundleID() {
        return cargoBundleId;
    }

    /**
     * @return
     */
    public int getScheduleID() {
        return scheduleId;
    }

    /**
     * @return
     */
    public ImmutableList<Integer> getConsist() {
        return wagonTypes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TrainModel) {
            TrainModel test = (TrainModel) obj;

            return cargoBundleId == test.cargoBundleId && engineTypeId == test.engineTypeId && wagonTypes.equals(test.wagonTypes) && scheduleId == test.scheduleId;
        }
        return false;
    }
}