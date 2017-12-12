/*
 * TrackPieceView.java
 *
 * Created on 20 July 2001, 17:46
 */

package jfreerails.client.trackview;

import java.awt.Image;
import jfreerails.common.exception.FreerailsException;
import jfreerails.common.IntPoint;
import jfreerails.common.trackmodel.EightRotationsOfTrackPieceProducer;
import jfreerails.lib.ImageSplitter;
import javax.swing.ImageIcon;



/**
 *
 * @author  Luke Lindsay
 * @version 
 */
public class TrackPieceView {

    Image[] trackPieceIcons=new Image[512];
    
    /** Creates new TrackPieceView */
    public TrackPieceView(int [] trackTemplatesPrototypes, ImageSplitter trackImageSplitter) throws FreerailsException{
      
         
        for(int i=0;i<trackTemplatesPrototypes.length;i++){
            
            /* Check for invalid parameters. */
            if( ( trackTemplatesPrototypes[i]>511 ) || ( trackTemplatesPrototypes[i] < 0 ) ) {
                throw new FreerailsException( "trackTemplate = " + trackTemplatesPrototypes[i] + ", it should be in the range 0-511" );
            }
            
            /* Grab the images for those track pieces that are legal. */
            for (int j=0;j<trackTemplatesPrototypes.length;j++){
                int [] rotationsOfTrackTemplate=EightRotationsOfTrackPieceProducer.getRotations(trackTemplatesPrototypes[j]);
               
                for (int k=0;k<rotationsOfTrackTemplate.length;k++){
                
                    if(trackPieceIcons[rotationsOfTrackTemplate[k]]==null){
                        trackPieceIcons[rotationsOfTrackTemplate[k]]=trackImageSplitter.get_tile_from_grid(k,j).getImage();
                        
                    }
                }
        }
        
    }
        
        
        
    }

    public Image getTrackPieceIcon(int trackTemplate)  throws FreerailsException {
          if( ( trackTemplate>511 ) || ( trackTemplate < 0 ) ) {
                throw new FreerailsException( "trackTemplate = " + trackTemplate + ", it should be in the range 0-511" );
            }
         
        return trackPieceIcons[trackTemplate];
    }
    
}
