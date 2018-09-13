package robot;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class Standard extends Robot{

	public Standard(IMailDelivery delivery, IMailPool mailPool, boolean strong) {
		super(delivery, mailPool, strong, 4);
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
    	if (deliveryItem != null && deliveryItem.getFragile() || !tube.isEmpty() && tube.peek().getFragile()) throw new FragileItemBrokenException();
        if(current_floor < destination){
            current_floor++;
        }
        else{
            current_floor--;
        }
    }

}
