package robot;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class Careful extends Robot{
	boolean stop = true;
	public Careful(IMailDelivery delivery, IMailPool mailPool, boolean strong) {
		super(delivery, mailPool, strong, 3);
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Sets the route for the robot
     */
    private void setRoute() throws ItemTooHeavyException{
    	/** Pop the item from the StorageUnit */
        deliveryItem = tube.pop();
        /** Set the destination floor */
        destination_floor = deliveryItem.getDestFloor();
    }

    /**
     * Generic function that moves the robot towards the destination
     * @param destination the floor towards which the robot is moving
     */
    private void moveTowards(int destination) throws FragileItemBrokenException {
	    if(stop) {
	    	stop = false;
	    }
	    else {
			if(current_floor < destination){
		        current_floor++;
		    }
		    else{
		        current_floor--;
		    }
			stop = true;
	    }
    }

}
