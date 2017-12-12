/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.list;

import java.util.*;

import jfreerails.element.RRCompany;
import jfreerails.type.IndustryType;
import jfreerails.misc.IndustryTypesFactory;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class IndustryList {

   ///////////////////////////////////////
   // associations

    public RRCompany rRCompany;
    public IndustryType industryType;
    public IndustryTypesFactory industryTypesFactory;
    public GameElementsList gameElementsList;


   ///////////////////////////////////////
   // access methods for associations


    public RRCompany getRRCompany() {
        return rRCompany;
    }
    public void setRRCompany(RRCompany rRCompany) {
        if (this.rRCompany != rRCompany) {
            this.rRCompany = rRCompany;
            if (rRCompany != null)
                rRCompany.setIndustryList(this);  
        }      
    } 

    public IndustryType getIndustryType() {
        return industryType;
    }
    public void setIndustryType(IndustryType industryType) {
        if (this.industryType != industryType) {
            if (this.industryType != null) 
                this.industryType.removeIndustryList(this);     
            this.industryType = industryType;
            if (industryType != null)
                industryType.addIndustryList(this);  
        }
    } 

    public IndustryTypesFactory getIndustryTypesFactory() {
        return industryTypesFactory;
    }
    public void setIndustryTypesFactory(IndustryTypesFactory industryTypesFactory) {
        if (this.industryTypesFactory != industryTypesFactory) {
            this.industryTypesFactory = industryTypesFactory;
            if (industryTypesFactory != null)
                industryTypesFactory.setIndustryList(this);  
        }      
    } 

    public GameElementsList getGameElementsList() {
        return gameElementsList;
    }
    public void setGameElementsList(GameElementsList gameElementsList) {
        if (this.gameElementsList != gameElementsList) {
            this.gameElementsList = gameElementsList;
            if (gameElementsList != null)
                gameElementsList.setIndustryList(this);  
        }      
    } 



}





