package robot;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class Big extends Robot{

	public Big(IMailDelivery delivery, IMailPool mailPool, boolean strong) {
		super(delivery, mailPool, strong, 6);
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Sets the route for the robot
     */
    private void setRoute() throws ItemTooHeavyException{
        
    }


}
