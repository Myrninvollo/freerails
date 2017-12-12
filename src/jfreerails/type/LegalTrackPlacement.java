/*
 * LegalTrackPlacement.java
 *
 * Created on 22 January 2002, 10:20
 */

package jfreerails.type;
import java.util.Iterator;
import java.util.HashSet;
/**
 *
 * @author  lindsal
 * @version 
 */
public final class LegalTrackPlacement {    
    /**
     * @associates Object 
     */
    private final HashSet terrainTypes = new HashSet();
    
    private final PlacementRule placementRule;

    /** Creates new LegalTrackPlacement */
    public LegalTrackPlacement(HashSet types, PlacementRule placementRule) {
        this.placementRule=placementRule;
        Iterator iterator=types.iterator();
        while(iterator.hasNext()){
            String typeName=(String)(iterator.next());
            terrainTypes.add(typeName);       
        }
    }
    public boolean canBuildOnThisTerrain(String terrainType){
        if(PlacementRule.ONLY_ON_THESE==placementRule){
            return terrainTypes.contains(terrainType);
        }else{
            return !terrainTypes.contains(terrainType);
        }  
    }
    
    final public static  class PlacementRule {
        
        private PlacementRule(){}
        
        public static final PlacementRule ONLY_ON_THESE=new PlacementRule();
        
        public static final PlacementRule ANYWHERE_EXCEPT_ON_THESE=new PlacementRule();
    }

}
