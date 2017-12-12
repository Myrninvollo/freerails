/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.controller;

import java.util.*;

import java.lang.String;
import jfreerails.element.RRCompany;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author Luke Lindsay*/

public class TrackBuilderRules {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private int milesOfTrack;
/**
 * Represents ...

 */

    private boolean trackBuildingAllowed;
/**
 * Represents ...

 */

    private String reasonTrackBuildingForbiden;
/**
 * Represents ...

 */

    private boolean mustConectToExistingTrack;

   ///////////////////////////////////////
   // associations

    public RRCompany rRCompany;


  ///////////////////////////////////////
  //access methods for attributes

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

   ///////////////////////////////////////
   // access methods for associations


    public RRCompany getRRCompany() {
        return rRCompany;
    }
    public void setRRCompany(RRCompany rRCompany) {
        if (this.rRCompany != rRCompany) {
            this.rRCompany = rRCompany;
            if (rRCompany != null)
                rRCompany.setTrackBuilderRules(this);  
        }      
    } 



}





