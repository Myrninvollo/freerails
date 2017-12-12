
/*
 * TrackPieceFactoryTest.java
 * JUnit based test
 *
 * Created on 24 January 2002, 16:16
 */                

package jfreerails.world.std_track;

import jfreerails.world.TrackRuleList;
import jfreerails.world.flat.*;
import jfreerails.world.tilemap.MapFixtureFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 *
 * @author lindsal
 */                                
public class TrackRuleListTest extends TestCase {
    
    TrackRuleList trackRules;
    
    
    public TrackRuleListTest(java.lang.String testName) {
        super(testName);
    }        
    
    public static void main(java.lang.String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(TrackRuleListTest.class);
        return suite;
    }
    protected void setUp(){
           trackRules=MapFixtureFactory.generateTrackRuleList();
         
    }
    public void testTrackPieceFactory(){        
        assertNotNull(trackRules);
    }
    
    public void testTrackPieceIsLegal(){
       
        TrackConfiguration config=TrackConfiguration.getFlatInstance("000010000");        
        assertEquals(true, trackRules.getTrackRule(0).trackPieceIsLegal(config));
        
        config=TrackConfiguration.getFlatInstance("111110000");        
        assertEquals(false, trackRules.getTrackRule(0).trackPieceIsLegal(config));
    }
    
    public void testGetTrackPiece(){
  
        TrackConfiguration config=TrackConfiguration.getFlatInstance("000010000"); 
        
        TrackPiece trackPiece=trackRules.getTrackRule(0).getTrackPiece(config);
        //Check that it gave us a track piece.
        assertNotNull(trackPiece);
        
        //Check that the track piece's config. and type is what we asked for.
        assertEquals(config, trackPiece.getTrackConfiguration());
        assertEquals(trackRules.getTrackRule(0), trackPiece.getTrackRule());
    }

}
