package robot;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class StandardRobot extends Robot{

	public StandardRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, true, 4);
		// TODO Auto-generated constructor stub
	}
}
