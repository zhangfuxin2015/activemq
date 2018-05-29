package Thread.Thread06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangfuxin on 2017/5/15.
 */
public class UseThreadPoolExecutor1 {
    public static void main(String[] args) {
        /**
         * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
         * 若大于corePoolSize，则会将任务加入队列，
         * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
         * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
         *
         */

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 				//coreSize   初始化的线程
                2, 				//MaxSize  最大线程
                60, 			//60
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)			//指定一种队列 （有界队列）
                //new LinkedBlockingQueue<Runnable>()
                //拒绝策略，可以用jdk提供的几个策略
                //AbortPolicy：直接抛出异常组织系统正常工作
                //CallerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。
                //DiscardOldestPolicy：丢弃最老的一个请求，尝试再次提交当前任务。
                //DiscardPolicy：丢弃无法处理的任务，不给予任何处理。
                //如果需要自定义拒绝策略可以实现RejectedExecutionHandler接口。
                ,new MyRejected()
        );
        //当提交mt1时，会凑核心线程，然后 mt2，mt3,mt4会放入到有界队列里面。 当执行mt5的时候
        //发现队列满了，则会 启动最大线程， 当执行mt6的时候，则会抛出异常，

        MyTask mt1 = new MyTask(1, "任务1");
        MyTask mt2 = new MyTask(2, "任务2");
        MyTask mt3 = new MyTask(3, "任务3");
        MyTask mt4 = new MyTask(4, "任务4");
        MyTask mt5 = new MyTask(5, "任务5");
        MyTask mt6 = new MyTask(6, "任务6");

        pool.execute(mt1);
        pool.execute(mt2);
        pool.execute(mt3);
        pool.execute(mt4);
        pool.execute(mt5);
        pool.execute(mt6);

        pool.shutdown();

    }
}
