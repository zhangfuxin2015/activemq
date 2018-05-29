package Thread.Thread03;

import java.util.Vector;

/**
 *同步类容器都是线程安全的，但在某些场景下可能需要加锁来保护复合操作。
 * 复合类操作如：迭代（反复访问元素，遍历完容器中所有的元素）、跳转（根据指定的顺序找到当前元素的下一个元素）、
 * 以及条件运算。这些复合操作在多线程并发地修改容器时，可能会表现出意外的行为，最经典的便是ConcurrentModificationException，
 * 原因是当容器迭代的过程中，被并发的修改了内容，这是由于早期迭代器设计的时候并没有考虑并发修改的问题。
 同步类容器：如古老的Vector、HashTable。这些容器的同步功能其实都是有JDK的Collections.synchronized***等工厂方法去创建实现的。
 其底层的机制无非就是用传统的synchronized关键字对每个公用的方法都进行同步，使得每次只能有一个线程访问容器的状态。
 这很明显不满足我们今天互联网时代高并发的需求，在保证线程安全的同时，也必须要有足够好的性能。
 */
public class Tickets {
    public static void main(String[] args) {
        //初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap

        final Vector<String> tickets = new Vector<String>();
        for(int i = 1; i<= 1000; i++){
            tickets.add("火车票"+i);
        }

        for(int i = 1; i <=10; i ++){
            //
            new Thread("线程"+i){
                public void run(){
                    //iterator...
                }
            }.start();

            new Thread("线程"+i){
                public void run(){
                    while(true){
                        if(tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }.start();
        }
    }
}
