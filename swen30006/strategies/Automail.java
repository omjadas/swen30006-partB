package strategies;

import automail.IMailDelivery;
import robot.Big;
import robot.Careful;
import robot.Robot;
import robot.Standard;
import robot.Weak;

public class Automail {
	      
    public Robot[] robot;
    public IMailPool mailPool;
    
    public Automail(IMailPool mailPool, IMailDelivery delivery) {
    	// Swap between simple provided strategies and your strategies here
    	    	
    	/** Initialize the MailPool */
    	
    	this.mailPool = mailPool;
    	
        /** Initialize the RobotAction */
    	boolean weak = false;  // Can't handle more than 2000 grams
    	boolean strong = true; // Can handle any weight that arrives at the building
    	
    	/** Initialize robots */
    	robot = new Robot[8];
    	robot[0] = new Big(delivery, mailPool);
    	robot[1] = new Careful(delivery, mailPool);
    	robot[2] = new Standard(delivery, mailPool);
    	robot[3] = new Weak(delivery, mailPool);
    	robot[4] = new Big(delivery, mailPool);
    	robot[5] = new Careful(delivery, mailPool);
    	robot[6] = new Standard(delivery, mailPool);
    	robot[7] = new Weak(delivery, mailPool);
    }
    
}
