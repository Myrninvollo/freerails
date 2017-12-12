
/*
 * TrackPieceViewList.java
 *
 * Created on 21 July 2001, 01:04
 */
package jfreerails.client.trackview;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import jfreerails.world.track.TrackRule;
import jfreerails.world.track.TrackRuleList;
import jfreerails.world.track.TrackConfiguration;
import jfreerails.world.track.NullTrackType;
import sun.security.krb5.internal.i;

final public class TrackPieceViewList {

	private final TrackPieceView[] trackPieceViewArray;

	public TrackPieceView[] getTrackPieceViewArray() {
		return trackPieceViewArray;
	}

	public TrackPieceView getTrackPieceView(int i) {
		if (NullTrackType.NULL_TRACK_TYPE_RULE_NUMBER == i) {
			return NullTrackPieceView.instance;
		} else {
			return trackPieceViewArray[i];
		}
	}

	/** Creates new TrackPieceViewList */

	public TrackPieceViewList(TrackPieceView[] trackPieceViews) {

		trackPieceViewArray = new TrackPieceView[trackPieceViews.length];
		for (int i = 0; i < trackPieceViews.length; i++) {
			TrackPieceView trackPieceView = trackPieceViews[i];
			if (null == trackPieceView) {
				throw new java.lang.IllegalArgumentException();
			}
			trackPieceViewArray[i] = trackPieceView;
		}

	}
	public TrackPieceViewList(ArrayList trackPieceViewArrayList) {
		trackPieceViewArray = new TrackPieceView[trackPieceViewArrayList.size()];
		for (int i = 0; i < trackPieceViewArrayList.size(); i++) {
			TrackPieceView trackPieceView =
				(TrackPieceView) (trackPieceViewArrayList.get(i));
			trackPieceViewArray[i] = trackPieceView;
		}
	}

	public boolean validate(TrackRuleList trackRuleList) {

		boolean okSoFar = true;
		for (int i = 0; i < trackRuleList.getLength(); i++) {
			TrackRule trackRule = trackRuleList.getTrackRule(i);
			Iterator legalConfigurationsIterator =
				trackRule.getLegalConfigurationsIterator();
			TrackPieceView trackPieceView = this.getTrackPieceView(i);
			if (null == trackPieceView) {
				System.out.println(
					"No track piece view for the following track type: " + trackRule.getTypeName());
				return false;
			} else {
				while (legalConfigurationsIterator.hasNext()) {
					TrackConfiguration trackConfig=
						(TrackConfiguration) legalConfigurationsIterator.next();
						int trackGraphicsNo=trackConfig.getTrackGraphicsNumber();
						Image img=trackPieceView.getTrackPieceIcon(trackGraphicsNo);
						if(null==img){
							System.out.println(
					"No track piece image for the following track type: " + trackRule.getTypeName()+", with configuration: "+trackGraphicsNo);
							okSoFar=false;
						}

				}
			}
		}
		return okSoFar;
	}

}