/**
 *
 *
 *
 *
 *
 */

package jfreerails.controller;



/**
 *
 *
 *
 * @author lindsal
 */

public class MoveProducer {


   // associations

    public GUI gUI;
    public MoveReceiver moveReceiver;



   // access methods for associations


    public GUI getGUI() {
        return gUI;
    }
    public void setGUI(GUI gUI) {
        if (this.gUI != gUI) {
            this.gUI = gUI;
            if (gUI != null)
                gUI.setMoveProducer(this);
        }
    }

    public MoveReceiver getMoveReceiver() {
        return moveReceiver;
    }
    public void setMoveReceiver(MoveReceiver moveReceiver) {
            this.moveReceiver = moveReceiver;
    }





}





