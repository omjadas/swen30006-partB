package robots;

import automail.IMailDelivery;
import strategies.IMailPool;

/**
 * Standard robot is able to carry 4 items at once
 * Group 40
 */
public class StandardRobot extends Robot{
	private static final int MAX_ITEMS = 4;
	public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, true, MAX_ITEMS);
	}
}
