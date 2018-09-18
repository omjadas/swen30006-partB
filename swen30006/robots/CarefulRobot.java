package robots;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import strategies.IMailPool;

/**
 * careful robot is able to carry 3 normal items or 1 fragile item at once
 * Group 40
 */

public class CarefulRobot extends Robot{
	private static final int MAX_ITEMS = 3;
	private boolean stop = true;
	public CarefulRobot(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, true, MAX_ITEMS);
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Generic function that moves the robot towards the destination
     * @param destination the floor towards which the robot is moving
     * @throws FragileItemBrokenException 
     * The robot stop once for every step()
     */
    protected void moveTowards(int destination) throws FragileItemBrokenException {
	    //skips one step
    	if(stop) {
	    	stop = false;
	    }
    	//make a move on the second step
	    else {
	    	if(current_floor < destination){
	            current_floor++;
	        }
	        else{
	            current_floor--;
	        }
	    }
    }

}
