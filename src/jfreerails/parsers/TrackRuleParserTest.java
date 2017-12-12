
/*
 * TrackRuleParserTest.java
 * JUnit based test
 *  Tests that the Track_TileParser classes generate the
 * expected track rules from a simple track rule xml file.
 * Created on 21 January 2002, 20:04
 */

package jfreerails.parsers;
/**
 *
 * @author lindsal
 */
public class TrackRuleParserTest extends junit.framework.TestCase {
    
    jfreerails.list.TrackRuleList trackRuleList;
    
    public TrackRuleParserTest(java.lang.String testName) {
        super(testName);
    }
    
    public static void main(java.lang.String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
    public static junit.framework.Test suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite(TrackRuleParserTest.class);
        return suite;
    }
    
    protected void setUp(){
        java.net.URL url = TrackRuleParserTest.class.getResource("/jfreerails/unittest/fixture/simple_track.xml");
        Track_TilesHandlerImpl track_TilesHandlerImpl;
        track_TilesHandlerImpl=new Track_TilesHandlerImpl(url);
        trackRuleList=track_TilesHandlerImpl.getTrackRuleList();
    }
    
    public void testTrack_TilesParser(){
        
        //Check for nulls
        assertNotNull(trackRuleList);
        jfreerails.type.TrackRule rule0, rule1;
        rule0=trackRuleList.getTrackRule(0);
        assertNotNull(rule0);
        rule1=trackRuleList.getTrackRule(1);
        assertNotNull(rule1);
        
        //Check the rules have the expected properties.
        assertEquals(false, rule0.isDoubleTrackEnabled());
        assertEquals("standard track", rule0.getTypeName());
        assertEquals(-1, rule0.getMaximumConsecutivePieces());
        
        //Check the rules have the expected properties.
        assertEquals(false, rule1.isDoubleTrackEnabled());
        assertEquals("bridge", rule1.getTypeName());
        assertEquals(1, rule1.getMaximumConsecutivePieces());
        
        //Check that the rules allow  the expected configurations.
        String strConfiguation="010010000";
        int configuation=jfreerails.world.flat.LegalTrackConfigurations.stringTemplate2Int(strConfiguation);
        assertEquals(true, rule0.testTrackPieceLegality(configuation));
        assertEquals(true, rule1.testTrackPieceLegality(configuation));
        
        //Check that the rules disallow  the expected configurations.
        strConfiguation="010010110";
        configuation=jfreerails.world.flat.LegalTrackConfigurations.stringTemplate2Int(strConfiguation);
        assertEquals(false, rule0.testTrackPieceLegality(configuation));
        assertEquals(false, rule1.testTrackPieceLegality(configuation));
        
        //Check that the rules allow the track to be placed on the expected terrain.
        assertEquals(true, rule0.canBuildOnThisTerrainType("Clear"));
        assertEquals(true, rule1.canBuildOnThisTerrainType("River"));
        
        //Check that the rules disallow the track to be placed on the expected terrain.
        assertEquals(false, rule0.canBuildOnThisTerrainType("River"));
        assertEquals(false, rule1.canBuildOnThisTerrainType("Clear"));
        
        
    }
}
