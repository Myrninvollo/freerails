/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.WagonModel;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeTrainConsistMove extends AbstractTrainMove {

   ///////////////////////////////////////
   // associations

    public Vector wagonModel = new Vector();
    public Vector wagonModel_1 = new Vector();


   ///////////////////////////////////////
   // access methods for associations


    public Vector getWagonModel() {
        return wagonModel;
    }
    public void addWagonModel(WagonModel wagonModel) {
        if (! this.wagonModel.contains(wagonModel)) {     
            this.wagonModel.addElement(wagonModel);  
        }
    }
    public void removeWagonModel(WagonModel wagonModel) {    
        this.wagonModel.removeElement(wagonModel);        
    }

    public Vector getWagonModel_1() {
        return wagonModel_1;
    }
    public void addWagonModel_1(WagonModel wagonModel) {
        if (! this.wagonModel_1.contains(wagonModel)) {     
            this.wagonModel_1.addElement(wagonModel);  
        }
    }
    public void removeWagonModel_1(WagonModel wagonModel) {    
        this.wagonModel_1.removeElement(wagonModel);        
    }



}





