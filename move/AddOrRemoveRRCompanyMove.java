/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.element.Company;
import java.lang.String;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class AddOrRemoveRRCompanyMove extends AbstractFinancialMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public Company owner;
/**
 * Represents ...

 */

    public String name;
/**
 * Represents ...

 */

    public int noBankruptcies;

   ///////////////////////////////////////
   // associations

    public Vector addOrRemoveBondMove = new Vector();
    public Vector addOrRemoveStockMove = new Vector();


  ///////////////////////////////////////
  //access methods for attributes

    public Company getOwner() {
        return owner;
    }
    public void setOwner(Company owner) {
        this.owner = owner;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNoBankruptcies() {
        return noBankruptcies;
    }
    public void setNoBankruptcies(int noBankruptcies) {
        this.noBankruptcies = noBankruptcies;
    }

   ///////////////////////////////////////
   // access methods for associations


    public Vector getAddOrRemoveBondMove() {
        return addOrRemoveBondMove;
    }
    public void addAddOrRemoveBondMove(AddOrRemoveBondMove addOrRemoveBondMove) {
        if (! this.addOrRemoveBondMove.contains(addOrRemoveBondMove)) {     
            this.addOrRemoveBondMove.addElement(addOrRemoveBondMove);  
        }
    }
    public void removeAddOrRemoveBondMove(AddOrRemoveBondMove addOrRemoveBondMove) {    
        this.addOrRemoveBondMove.removeElement(addOrRemoveBondMove);        
    }

    public Vector getAddOrRemoveStockMove() {
        return addOrRemoveStockMove;
    }
    public void addAddOrRemoveStockMove(AddOrRemoveStockMove addOrRemoveStockMove) {
        if (! this.addOrRemoveStockMove.contains(addOrRemoveStockMove)) {     
            this.addOrRemoveStockMove.addElement(addOrRemoveStockMove);  
        }
    }
    public void removeAddOrRemoveStockMove(AddOrRemoveStockMove addOrRemoveStockMove) {    
        this.addOrRemoveStockMove.removeElement(addOrRemoveStockMove);        
    }



}





