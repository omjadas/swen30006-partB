package robot;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class Standard extends Robot{

	public Standard(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, true, 4);
		// TODO Auto-generated constructor stub
	}
}
