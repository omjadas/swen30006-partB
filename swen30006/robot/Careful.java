package robot;

import automail.IMailDelivery;
import exceptions.FragileItemBrokenException;
import exceptions.ItemTooHeavyException;
import strategies.IMailPool;

public class Careful extends Robot{
	boolean stop = true;
	public Careful(IMailDelivery delivery, IMailPool mailPool) {
		super(delivery, mailPool, true, 3);
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Generic function that moves the robot towards the destination
     * @param destination the floor towards which the robot is moving
     * @throws FragileItemBrokenException 
     */
    protected void moveTowards(int destination) throws FragileItemBrokenException {
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
	    }
    }

}
