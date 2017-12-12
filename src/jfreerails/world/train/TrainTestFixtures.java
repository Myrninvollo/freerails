package jfreerails.world.train;

import jfreerails.world.common.Money;

/**
 * @author Luke Lindsay 20-Oct-2002
 *
 */
public class TrainTestFixtures {
	
	public static final EngineType DEFAULT_ENGINE_TYPE = new EngineType("Default Engine", 1000, new Money(100), 25) ;
	
	public static final WagonType MAIL = new WagonType("Mail", WagonType.MAIL);

}
