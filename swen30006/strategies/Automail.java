package strategies;

import java.util.ArrayList;


import automail.IMailDelivery;
import robots.BigRobot;
import robots.CarefulRobot;
import robots.Robot;
import robots.StandardRobot;
import robots.WeakRobot;

/**
 * Group 40
 *
 */

public class Automail {
	      
    public Robot[] robot;
    public IMailPool mailPool;
    
    public Automail(IMailPool mailPool, IMailDelivery delivery, ArrayList<String> robotStrings) {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    	
    	/** 
    	 * Initialize robots 
    	 * reads robot information from properties file
    	 */
    	robot = new Robot[robotStrings.size()];
    	int i = 0;
    	for (String type: robotStrings) {
    		if (type.equals("Big")) {
    			robot[i] = new BigRobot(delivery, mailPool);
    		} else if (type.equals("Careful")) {
    			robot[i] = new CarefulRobot(delivery, mailPool); 
    		} else if (type.equals("Standard")) {
    			robot[i] = new StandardRobot(delivery, mailPool);
    		} else if (type.equals("Weak")) {
    			robot[i] = new WeakRobot(delivery, mailPool);
    		}
    		i++;
    	}
    }
    
}
