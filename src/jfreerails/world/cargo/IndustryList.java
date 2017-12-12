package jfreerails.world.cargo;



/**
 *
 *
 *
 * @author lindsal
 */

public class IndustryList {


	public IndustryType industryType;
	public IndustryTypesFactory industryTypesFactory;




	public IndustryType getIndustryType() {
		return industryType;
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



}