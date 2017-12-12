
/*
 * TrackTileMapTest.java
 * JUnit based test
 *
 * Created on 24 January 2002, 02:10
 */                

package jfreerails.world.std_track;

import junit.framework.TestSuite;
import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.Test;
import java.awt.Dimension;
import java.awt.Point;
import jfreerails.list.TrackRuleList;
import jfreerails.unittest.fixture.MapFixtureFactory;
import jfreerails.world.flat.LegalTrackConfigurations;
import jfreerails.type.TrackRule;
/**
 *
 * @author lindsal
 */                                
public class TrackTileMapTest extends TestCase {
     TrackRuleList trackRules;
    TrackTileMap map;
    public TrackTileMapTest(java.lang.String testName) {
        super(testName);
    }        
    
    public static void main(java.lang.String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(TrackTileMapTest.class);
        return suite;
    }
    protected void setUp(){
         trackRules=MapFixtureFactory.generateTrackRuleList();
       
         map=new TrackTileMapImpl(new Dimension(20,20));
        
    }
   public void testTrackTileMap(){
      setUp();
      TrackPiece trackPiece= map.getTrackPiece(new Point(0,0));
      
       assertNotNull(trackPiece);
       int n=trackPiece.getTrackGraphicNumber();
       assertEquals(NullTrackType.NULL_TRACK_TYPE_RULE_NUMBER, n);
   }
   
   
   
   public void testTrackRuleList(){
       setUp();
       
       //Check for expected number of rules.
       int length =trackRules.getLength();
       assertEquals(3, length);
       
       //Check that they are non null and in the right place in the list.
       for (int i=0;i<3;i++){
           assertNotNull(trackRules.getTrackRule(i));
           assertEquals(i, trackRules.getTrackRule(i).getRuleNumber());
       }
       
       //Check that the placement rules are correctly setup,
       //(we should be able to build anywhere except type 1 on "mountain")
       for (int i=0;i<3;i++){
           TrackRule trackRule=trackRules.getTrackRule(i);
           assertEquals(true, trackRule.canBuildOnThisTerrainType("clear"));
           if(i!=1){
               assertEquals(true, trackRule.canBuildOnThisTerrainType("mountain"));
               
           }else{
               assertEquals(false, trackRule.canBuildOnThisTerrainType("mountain"));
           }           
       }
       
       //Check that the configuration rules are correctly setup.
       
       //This work for all of them..
       String templateString ="000010000";
       int templateInt=LegalTrackConfigurations.stringTemplate2Int(templateString);
       for (int i=0;i<3;i++){
           TrackRule trackRule=trackRules.getTrackRule(i);
           assertEquals(true, trackRule.testTrackPieceLegality(templateInt));            
       }
       //..this for the first two..
       templateString ="000111000";
       templateInt=LegalTrackConfigurations.stringTemplate2Int(templateString);
       for (int i=0;i<3;i++){
           TrackRule trackRule=trackRules.getTrackRule(i);
            if(i!=2){
              assertEquals(true, trackRule.testTrackPieceLegality(templateInt));    
               
           }else{
              assertEquals(false, trackRule.testTrackPieceLegality(templateInt));    
           }        
       }
       //..and this for non..
       templateString ="110111000";
       templateInt=LegalTrackConfigurations.stringTemplate2Int(templateString);
       for (int i=0;i<3;i++){
           TrackRule trackRule=trackRules.getTrackRule(i);
           
              assertEquals(false, trackRule.testTrackPieceLegality(templateInt));    
                
       }
  
   }
  



}
