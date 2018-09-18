package robots;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class BigRobot extends Robot{
	private static final int MAX_ITEMS = 6;
	public BigRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, true, MAX_ITEMS);
	}
	
}
