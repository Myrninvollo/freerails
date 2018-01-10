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

/*
 * TrainView.java
 *
 */
package freerails.client.view;

import freerails.client.renderer.RendererRoot;
import freerails.controller.ModelRoot;
import freerails.util.ImmutableList;
import freerails.world.KEY;
import freerails.world.NonNullElementWorldIterator;
import freerails.world.ReadOnlyWorld;
import freerails.world.WorldListListener;
import freerails.world.player.FreerailsPrincipal;
import freerails.world.train.ImmutableSchedule;
import freerails.world.train.TrainModel;
import freerails.world.train.TrainOrdersModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This JPanel displays an engine and a number of wagons.
 */
public class TrainListCellRenderer extends JPanel implements View,
        ListCellRenderer, WorldListListener {
    private static final long serialVersionUID = 3546076964969591093L;
    private final Color backgoundColor = (java.awt.Color) javax.swing.UIManager
            .getDefaults().get("List.background");
    private final Color selectedColor = (java.awt.Color) javax.swing.UIManager
            .getDefaults().get("List.selectionBackground");
    private final Color selectedColorNotFocused = Color.LIGHT_GRAY;
    private ReadOnlyWorld w;
    private RendererRoot vl;
    private int trainNumber = -1;
    private int scheduleOrderNumber;
    private int scheduleID = -1;
    private int height = 100;
    private FreerailsPrincipal principal;
    private Image[] images = new Image[0];
    /**
     * Whether this JPanel should one of the trains orders from the schedule
     * instead of the trains current formation.
     */
    private boolean showingOrder = false;
    /**
     * If true, the train is drawn in the center to the JPanel; if false, the
     * train is drawn left aligned.
     */
    private boolean centerTrain = false;
    private int trainWidth = 0;

    /**
     *
     */
    public TrainListCellRenderer() {
        setOpaque(false);
    }

    /**
     * @param mr
     * @param vl
     */
    public TrainListCellRenderer(ModelRoot mr, RendererRoot vl) {
        setup(mr, vl, null);
        setBackground(backgoundColor);
    }

    /**
     * @param b
     */
    public void setCenterTrain(boolean b) {
        centerTrain = b;
    }

    /**
     * @param newTrainNumber
     */
    public void display(int newTrainNumber) {
        showingOrder = false;
        trainNumber = newTrainNumber;

        TrainModel train = (TrainModel) w.get(principal, KEY.TRAINS,
                trainNumber);
        display(train.getEngineType(), train.getConsist());
        resetPreferredSize();
    }

    private void display(int engine, ImmutableList<Integer> wagons) {
        images = new Image[1 + wagons.size()];
        // images[0] = vl.getTrainImages().getSideOnEngineImage(
        // train.getEngineType(), height);
        String engineFilename = vl.getEngineImages(engine).sideOnFileName;
        try {
            images[0] = vl.getScaledImage(engineFilename, height);
        } catch (IOException e) {
            throw new IllegalArgumentException(engineFilename);
        }
        for (int i = 0; i < wagons.size(); i++) {
            // images[i + 1] = vl.getTrainImages().getSideOnWagonImage(
            // order.consist.get(i), height);
            int wagonType = wagons.get(i);
            String wagonFilename = vl.getWagonImages(wagonType).sideOnFileName;
            try {
                images[i + 1] = vl.getScaledImage(wagonFilename, height);
            } catch (IOException e) {
                throw new IllegalArgumentException(wagonFilename);
            }

        }
    }

    /**
     * @param newTrainNumber
     * @param newScheduleOrderID
     */
    public void display(int newTrainNumber, int newScheduleOrderID) {
        showingOrder = true;
        trainNumber = newTrainNumber;
        scheduleOrderNumber = newScheduleOrderID;

        TrainModel train = (TrainModel) w.get(principal, KEY.TRAINS,
                trainNumber);
        scheduleID = train.getScheduleID();

        ImmutableSchedule s = (ImmutableSchedule) w.get(principal,
                KEY.TRAIN_SCHEDULES, scheduleID);
        TrainOrdersModel order = s.getOrder(newScheduleOrderID);

        // Set up the array of images.
        if (null != order.consist) {
            display(train.getEngineType(), order.consist);
        } else {
            images = new Image[0];
        }

        resetPreferredSize();
    }

    private void resetPreferredSize() {
        int width = 0;

        for (Image image : images) {
            width += image.getWidth(null);
        }

        trainWidth = width;
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * @param mr
     * @param vl
     * @param closeAction
     */
    public void setup(ModelRoot mr, RendererRoot vl, Action closeAction) {
        w = mr.getWorld();
        this.vl = vl;
        principal = mr.getPrincipal();
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        int trainID = NonNullElementWorldIterator
                .row2index(w, KEY.TRAINS, principal, index);
        display(trainID);

        if (isSelected) {
            if (list.isFocusOwner()) {
                setBackground(selectedColor);
            } else {
                setBackground(selectedColorNotFocused);
            }
        } else {
            setBackground(backgoundColor);
        }

        return this;
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * @param i
     */
    public void setHeight(int i) {
        height = i;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0;

        if (centerTrain) {
            x = (getWidth() - trainWidth) / 2;
        }

        for (Image image : images) {
            g.drawImage(image, x, 0, null);
            x += image.getWidth(null);
        }
    }

    /**
     * @param key
     * @param index
     * @param p
     */
    public void listUpdated(KEY key, int index, FreerailsPrincipal p) {
        if (showingOrder) {
            if (KEY.TRAIN_SCHEDULES == key && scheduleID == index) {
                display(trainNumber, scheduleOrderNumber);
            }
        } else {
            if (KEY.TRAINS == key && trainNumber == index) {
                display(trainNumber);
            }
        }
    }

    /**
     * @param key
     * @param index
     * @param p
     */
    public void itemAdded(KEY key, int index, FreerailsPrincipal p) {
    }

    /**
     * @param key
     * @param index
     * @param p
     */
    public void itemRemoved(KEY key, int index, FreerailsPrincipal p) {
    }
}