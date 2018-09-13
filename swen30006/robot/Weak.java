package robot;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;
import automail.MailItem;


public class Weak extends Robot {

	public Weak(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, false, 4);
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Sets the route for the robot
     */
    private void setRoute() throws ItemTooHeavyException{
    	/** Pop the item from the StorageUnit */
        deliveryItem = tube.pop();
        if (deliveryItem.getWeight() > 2000) throw new ItemTooHeavyException(); 
	    /** Set the destination floor */
	    destination_floor = deliveryItem.getDestFloor();
    }

}
