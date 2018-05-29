package Thread.Thread06;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangfuxin on 2017/5/15.
 */
public class UseThreadPoolExecutor2 implements Runnable{
    private static AtomicInteger count = new AtomicInteger(0);

    public void run() {
        try {
            int temp = count.incrementAndGet();
            System.out.println("����" + temp);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        //�õ��޽���У���ʼ����ִ�У�5���̣߳�Ȼ����뵽�����У��ȴ��������ִ���꣬����ִ�С�
    public static void main(String[] args) throws Exception{
        //System.out.println(Runtime.getRuntime().availableProcessors());
        BlockingQueue<Runnable> queue =
                new LinkedBlockingQueue<Runnable>();
                //new ArrayBlockingQueue<Runnable>(10);
        ExecutorService executor  = new ThreadPoolExecutor(
                5, 		//core
                10, //max
                120L, 	//2fenzhong
                TimeUnit.SECONDS,
                queue);

        for(int i = 0 ; i < 20; i++){
            executor.execute(new UseThreadPoolExecutor2());
        }
        Thread.sleep(1000);
        System.out.println("queue size:" + queue.size());		//10
        Thread.sleep(2000);
        executor.shutdown();
    }
}
