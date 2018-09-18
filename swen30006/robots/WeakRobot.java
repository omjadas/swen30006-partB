package robots;

import automail.IMailDelivery;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

/**
 * Weak robot is able to carry 4 items at once
 * The max weight the robot can carry is 2000
 * Group 40
 */
public class WeakRobot extends Robot {
	private static final int MAX_ITEMS = 4;
	private static final int MAX_WEIGHT = 2000;

	public WeakRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, false, MAX_ITEMS);
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Sets the route for the robot
     * if the item given to this robot is larger than 2000, 
     * a ItemTooHeavyException is thrown
     */
    protected void setRoute() throws ItemTooHeavyException{
    	/** Pop the item from the StorageUnit */
        deliveryItem = tube.pop();
        if (deliveryItem.getWeight() > MAX_WEIGHT) throw new ItemTooHeavyException(); 
	    /** Set the destination floor */
	    destination_floor = deliveryItem.getDestFloor();
    }
    
	/**
     * to retrieve the max weight for the weak robot
     * This mechanism is implemented to avoid using magic numbers in other functions
     */
    public static int getMaxWeight() {
    	return MAX_WEIGHT;
    }

}
