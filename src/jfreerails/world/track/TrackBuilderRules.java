/**
 *
 *
 *
 *
 *
 */
package jfreerails.world.track;




/**
 *
 *
 *
 * @author Luke Lindsay*/

public class TrackBuilderRules {




/**
 *

 */

    private int milesOfTrack;
/**
 *

 */

    private boolean trackBuildingAllowed;
/**
 *

 */

    private String reasonTrackBuildingForbiden;
/**
 *

 */

    private boolean mustConectToExistingTrack;

    public int getMilesOfTrack() {
        return milesOfTrack;
    }
    public void setMilesOfTrack(int milesOfTrack) {
        this.milesOfTrack = milesOfTrack;
    }
    public boolean getTrackBuildingAllowed() {
        return trackBuildingAllowed;
    }
    public void setTrackBuildingAllowed(boolean trackBuildingAllowed) {
        this.trackBuildingAllowed = trackBuildingAllowed;
    }
    public String getReasonTrackBuildingForbiden() {
        return reasonTrackBuildingForbiden;
    }
    public void setReasonTrackBuildingForbiden(String reasonTrackBuildingForbiden) {
        this.reasonTrackBuildingForbiden = reasonTrackBuildingForbiden;
    }
    public boolean getMustConectToExistingTrack() {
        return mustConectToExistingTrack;
    }
    public void setMustConectToExistingTrack(boolean mustConectToExistingTrack) {
        this.mustConectToExistingTrack = mustConectToExistingTrack;
    }


}





