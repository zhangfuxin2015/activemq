package Thread.Thread04;

public class UseQueue {
	/**
	 * ArrayBlockingQueue： 基于数组的阻塞队列实现，在ArrayBlockingQueue内部，
	 * 	维护了一个定长数组，以便缓存队列中的数据对象，其内部没实现读写分离，也就意味着生产和消费不
	 * 	能完全并行，长度是需要定义的，可以指定先进先出或者先进后出，也叫有界队列，
	 * 	在很多场合非常适合使用。
	 LinkedBlockingQueue：基于链表的阻塞队列，同ArrayBlockingQueue类似，
	 其内部也维持着一个数据缓冲队列（该队列由一个链表构成），LinkedBlockingQueue之所以能
	 够高效的处理并发数据，是因为其内部实现采用分离锁，从而实现生产者和消费者操作的完全并行运行。
	 他是一个无界队列。
	 PriorityBlockingQueue：基于优先级的阻塞队列（优先级的判断通过构造函数传入的Compator对象来决
	 定，也就是说传入队列的对象必须实现Comparable接口），在实现PriorityBlockingQueue时，
	 内部控制线程同步的锁采用的是公平锁，他也是一个无界的队列。
	 DelayQueue：带有延迟时间的Queue，其中的元素只有当其指定的延迟时间到了，才能够从队列中获取
	 到该元素。DelayQueue中的元素必须实现Delayed接口，DelayQueue是一个没有大小限制的队列，
	 应用场景很多，比如对缓存超时的数据进行移除、 任务超时处理、空闲连接的关闭等等。
	 SynchronousQueue ：一种没有缓冲的队列，生产者产生的数据直接会被消费者获取并消费。

	 * @param args
	 */
	public static void main(String[] args) {
		
//		SynchronousQueue<String> queue = new SynchronousQueue<String>();
//		queue.add("111");
		
		//高性能无阻塞无界队列：ConcurrentLinkedQueue
		
//		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
//		q.offer("a");
//		q.offer("b");
//		q.offer("c");
//		q.offer("d");
//		q.add("e");
//		System.out.println(q.poll()); 	//从头部取出一个元素 并且删除该元素
//		System.out.println(q.size());
//		System.out.println(q.peek());	//从头部取出一个元素 但是不删除
//		System.out.println(q.size());
		
		
		//阻塞队列  有界限
		/**
		LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>(10);
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		q.offer("f");
		List<String> list = new ArrayList<String>();
		System.out.println(q.drainTo(list, 3));
		System.out.println(list.size());
		for (String string : list) {
			System.out.println(string);
		}
		*/
		
	}
}
