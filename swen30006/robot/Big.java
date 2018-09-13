package robot;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class Big extends Robot{

	public Big(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, true, 6);
		// TODO Auto-generated constructor stub
	}
	
}
