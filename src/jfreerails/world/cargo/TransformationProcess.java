
package jfreerails.world.cargo;

import java.util.Vector;


final public class TransformationProcess {

  
    private final Vector cargoType = new Vector();
  
    private final SideEffect sideEffect;
    private final IndustryType industryType;


    public SideEffect getSideEffect() {
        return sideEffect;
    }

    public IndustryType getIndustryType() {
        return industryType;
    }
    
    public TransformationProcess(SideEffect s, IndustryType i){
    	sideEffect=s;
    	industryType=i;
    	
    }
}





