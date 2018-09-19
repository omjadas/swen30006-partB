package strategies;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.ListIterator;
import automail.MailItem;
import automail.PriorityMailItem;
import automail.StorageTube;
import exceptions.TubeFullException;
import robots.CarefulRobot;
import robots.Robot;
import robots.WeakRobot;
import exceptions.FragileItemBrokenException;

/**
 * Group 40
 *
 */
public class MyMailPool implements IMailPool {
	private class Item {
		int priority;
		int destination;
		boolean heavy;
		boolean fragile;
		MailItem mailItem;
		// Use stable sort to keep arrival time relative positions
		
		// check the properties of each item
		public Item(MailItem mailItem) {
			priority = (mailItem instanceof PriorityMailItem) ? ((PriorityMailItem) mailItem).getPriorityLevel() : 1;
			heavy = mailItem.getWeight() >= WeakRobot.getMaxWeight();
			destination = mailItem.getDestFloor();
			fragile = mailItem.getFragile();
			this.mailItem = mailItem;
		}
	}
	
	public class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item i1, Item i2) {
			int order = 0;
			if (i1.priority < i2.priority) {
				order = 1;
			} else if (i1.priority > i2.priority) {
				order = -1;
			} else if (i1.destination < i2.destination) {
				order = 1;
			} else if (i1.destination > i2.destination) {
				order = -1;
			}
			return order;
		}
	}
	
    /**
     * separate pool into normalPool and fragilePool
     * @param normalPool contains all mails that are normal
     * @param fragilePool contains all mails that are fragile, only careful robot is able to access this pool
     * @param robots is a list of robots in the simulation
     * @param lightCount is the number of light mails in the normalPool
     */
	private LinkedList<Item> normalPool;
	private LinkedList<Item> fragilePool;
	private LinkedList<Robot> robots;
	private int lightCount;

	public MyMailPool(){
		// Start empty
		normalPool = new LinkedList<Item>();
		fragilePool = new LinkedList<Item>();
		lightCount = 0;
		robots = new LinkedList<Robot>();
	}

	/**
	 * mailItems are added to the pool based on if they are fragile. 
	 * Record the number of light items contained in the normalPool.
	 * Sort the priorities of the pools based on the ItemComparator()
	 */
	public void addToPool(MailItem mailItem) {
		Item item = new Item(mailItem);
		if (item.fragile) {
			fragilePool.add(item);
		}else {
			normalPool.add(item);
			if (!item.heavy) lightCount++;
		}
		
		normalPool.sort(new ItemComparator());
		fragilePool.sort(new ItemComparator());
	}
	
	@Override
	public void step() throws FragileItemBrokenException {
		for (Robot robot: (Iterable<Robot>) robots::iterator) { fillStorageTube(robot); }
	}
	
	/**
	 * fill the storage tube with the max it can carry
	 * first check if the robot is a careful robot and if there are items in the fragile pool
	 * if the robot is careful and there is something in the fragile pool, then give one fragile item to the robot
	 * if either of the condition is not met, check if robot is weak
	 * if not weak, fill the robot tube with the max items from normalPool
	 * if weak, give items to the robot while keeping tracking the number of light items.
	 * Eventually, dispatch the robot
	 */
	private void fillStorageTube(Robot robot) throws FragileItemBrokenException {
		StorageTube tube = robot.getTube();
		StorageTube temp = new StorageTube(tube.MAXIMUM_CAPACITY);
		try {
			
			if (robot instanceof CarefulRobot && fragilePool.size() > 0) {
				temp.addItem(fragilePool.remove().mailItem);
			} else {
				if (robot.isStrong()) {
					while (temp.getSize() < temp.getMaxCapacity() && !normalPool.isEmpty()) {
						Item item = normalPool.remove();
						if (!item.heavy)
							lightCount--;
						temp.addItem(item.mailItem);
					}
				} else {
					ListIterator<Item> i = normalPool.listIterator();
					while (temp.getSize() < temp.getMaxCapacity() && lightCount > 0) {
						Item item = i.next();
						if (!item.heavy) {
							temp.addItem(item.mailItem);
							i.remove();
							lightCount--;
						}
					}
				}
				if (temp.getSize() > 0) {
					while (!temp.isEmpty()) tube.addItem(temp.pop());
					robot.dispatch();
				}
			}

			if (tube.getSize() > 0) {
				robot.dispatch();
			}
		} catch (TubeFullException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerWaiting(Robot robot) { // assumes won't be there
		if (robot.isStrong()) {
			robots.add(robot); 
		} else {
			robots.addLast(robot); // weak robot last as want more efficient delivery with highest priorities
		}
	}

	@Override
	public void deregisterWaiting(Robot robot) {
		robots.remove(robot);
	}

}
