/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.controller;

import java.util.*;

import jfreerails.element.RRCompany;
import java.lang.String;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class RROperator {

   ///////////////////////////////////////
   // associations

    public RRCompany rRCompany;


   ///////////////////////////////////////
   // access methods for associations


    public RRCompany getRRCompany() {
        return rRCompany;
    }
    public void setRRCompany(RRCompany rRCompany) {
        if (this.rRCompany != rRCompany) {
            this.rRCompany = rRCompany;
            if (rRCompany != null)
                rRCompany.setRROperator(this);  
        }      
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @param to ...
 * @param from ...
 */

    public void buildTrack(String to, String from) {
    }
/**
 * Does ...
 * 

 */

    public void repayBond() {
    }
/**
 * Does ...
 * 

 */

    public void giveMoney() {
    }
/**
 * Does ...
 * 

 */

    public void takeMoney() {
    }



}





