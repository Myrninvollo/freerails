/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.misc;


import jfreerails.list.IndustryList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class IndustryTypesFactory {

   ///////////////////////////////////////
   // associations

    public IndustryList industryList;


   ///////////////////////////////////////
   // access methods for associations


    public IndustryList getIndustryList() {
        return industryList;
    }
    public void setIndustryList(IndustryList industryList) {
        if (this.industryList != industryList) {
            this.industryList = industryList;
            if (industryList != null)
                industryList.setIndustryTypesFactory(this);  
        }      
    } 


  ///////////////////////////////////////
  // operations

/**
 * Does ...
 * 
 * @return A IndustryList with ...
 */

    public IndustryList generateIndustryTypesList() {
        return null;
    }



}





