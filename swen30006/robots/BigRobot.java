package robots;

import automail.IMailDelivery;

import strategies.IMailPool;

/**
 * Big robot is able to carry 6 items at once
 */

public class BigRobot extends Robot{
	private static final int MAX_ITEMS = 6;
	public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, true, MAX_ITEMS);
	}
	
}
