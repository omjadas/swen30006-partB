package strategies;

import automail.IMailDelivery;
import robot.BigRobot;
import robot.CarefulRobot;
import robot.Robot;
import robot.StandardRobot;
import robot.WeakRobot;

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
    	robot[0] = new BigRobot(delivery, mailPool);
    	robot[1] = new CarefulRobot(delivery, mailPool);
    	robot[2] = new StandardRobot(delivery, mailPool);
    	robot[3] = new WeakRobot(delivery, mailPool);
    	robot[4] = new BigRobot(delivery, mailPool);
    	robot[5] = new CarefulRobot(delivery, mailPool);
    	robot[6] = new StandardRobot(delivery, mailPool);
    	robot[7] = new WeakRobot(delivery, mailPool);
    }
    
}
