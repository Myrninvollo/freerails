package jfreerails.client.menu;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import jfreerails.controller.StationBuilder;
import jfreerails.world.track.TrackRule;
import jfreerails.world.track.TrackRuleList;

/**
 * This JPopupMenu displays the list of station types that
 * are available and builds the type that is selected. 
 * @author Luke Lindsay 08-Nov-2002
 *
 */
public class StationTypesPopup extends JPopupMenu {

	Point tileToBuildStationOn;

	StationBuilder stationBuilder;

	public StationTypesPopup() {

	}

	public void setup(StationBuilder sb) {
		stationBuilder = sb;
		TrackRuleList trackRuleList = sb.getTrackRuleList();

		for (int i = 0; i < trackRuleList.getLength(); i++) {
			final int trackRuleNumber = i;
			TrackRule trackRule = trackRuleList.getTrackRule(i);
			if (trackRule.isStation()) {
				String trackType = trackRule.getTypeName();
				JMenuItem rbMenuItem = new JMenuItem("Build " + trackType);
				rbMenuItem
					.addActionListener(new java.awt.event.ActionListener() {

					public void actionPerformed(
						java.awt.event.ActionEvent actionEvent) {
						stationBuilder.setStationType(trackRuleNumber);
						stationBuilder.buildStation(tileToBuildStationOn);
					}
				});

				this.add(rbMenuItem);
			}
		}

	}

	public void show(Component invoker, int x, int y, Point tile) {
		tileToBuildStationOn = tile;
		super.show(invoker, x, y);
	}
}
