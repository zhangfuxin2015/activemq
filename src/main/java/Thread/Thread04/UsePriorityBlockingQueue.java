package Thread.Thread04;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {

	/**
	 * 可以排序的 阻塞队列
	 * @param args
	 */
	public static void main(String[] args) {
		
		PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<Task>();
		Task t1 = new Task();
		t1.setId(1);
		t1.setName("task1");
		Task t2 = new Task();
		t2.setId(4);
		t2.setName("task2");
		Task t3 = new Task();
		t3.setId(2);
		t3.setName("task3");
		queue.add(t3);	//2
		queue.add(t1);	//1
		queue.add(t2);	//4
		
		for (Iterator iterator = queue.iterator(); iterator.hasNext();) {
			Task task = (Task) iterator.next();
			System.out.println(task.getId());
		}
	}
	
}
