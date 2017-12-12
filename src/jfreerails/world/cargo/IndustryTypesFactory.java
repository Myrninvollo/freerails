/**
 *
 *
 *
 *
 *
 */
package jfreerails.world.cargo;



/**
 *
 *
 *
 * @author lindsal
 */

public class IndustryTypesFactory {


   // associations

    public IndustryList industryList;



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





