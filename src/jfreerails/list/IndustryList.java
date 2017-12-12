package jfreerails.list;

import jfreerails.misc.IndustryTypesFactory;
import jfreerails.type.IndustryType;
import jfreerails.world.financial.RRCompany;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class IndustryList {

	public RRCompany rRCompany;
	public IndustryType industryType;
	public IndustryTypesFactory industryTypesFactory;
	public GameElementsList gameElementsList;

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