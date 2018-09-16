package strategies;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.function.Consumer;

import automail.MailItem;
import automail.PriorityMailItem;
import automail.StorageTube;
import exceptions.TubeFullException;
import robot.CarefulRobot;
import robot.Robot;
import robot.WeakRobot;
import exceptions.FragileItemBrokenException;

public class MyMailPool implements IMailPool {
	private class Item {
		int priority;
		int destination;
		boolean heavy;
		boolean fragile;
		MailItem mailItem;
		// Use stable sort to keep arrival time relative positions
		
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
	
	private void fillStorageTube(Robot robot) throws FragileItemBrokenException {
		StorageTube tube = robot.getTube();


		try { // Get as many items as available or as fit
			
			if (robot instanceof CarefulRobot && fragilePool.size()>0) {
				tube.addItem(fragilePool.remove().mailItem);
			}else {
				if (robot.isStrong()) {
					while(tube.getSize() < tube.getMaxCapacity() && !normalPool.isEmpty() ) {
						Item item = normalPool.remove();
						if (!item.heavy) lightCount--;
						tube.addItem(item.mailItem);
					}
				} else {
					ListIterator<Item> i = normalPool.listIterator();
					while(tube.getSize() < tube.getMaxCapacity() && lightCount > 0) {
						Item item = i.next();
						if (!item.heavy) {
							tube.addItem(item.mailItem);
							i.remove();
							lightCount--;
						}
					}
				}
			}

			if (tube.getSize() > 0) {
				robot.dispatch();
			}
		}
		catch(TubeFullException e){
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
