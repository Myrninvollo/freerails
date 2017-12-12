/*
 * Created on 15-Apr-2003
 * 
 */
package jfreerails.move;

import jfreerails.world.accounts.Bill;
import jfreerails.world.common.Money;
import jfreerails.world.top.KEY;
import jfreerails.world.train.TrainModel;

/**
 * This CompositeMove adds a train to the train list and charges the player for it.
 * @author Luke
 * 
 */
public class AddTrainMove extends CompositeMove {

	private AddTrainMove(Move[] moves) {
		super(moves);		
	}

	public static AddTrainMove generateMove(int i, TrainModel train, Money price){		
		Move m = new AddItemToListMove(KEY.TRAINS, i, train);		
		AddTransactionMove transactionMove = new AddTransactionMove(0 , new Bill(price));
		return new AddTrainMove(new Move[]{m, transactionMove}); 
	}		
}
