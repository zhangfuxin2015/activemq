package Thread.Thread02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile�ؼ��ֲ��߱�synchronized�ؼ��ֵ�ԭ���ԣ�ͬ����
 *
 */
public class VolatileNoAtomic extends Thread{
    //private static volatile int count = 0 ;
    private static AtomicInteger count = new AtomicInteger(0);

    private static void addCount(){
        for (int i = 0; i < 1000; i++) {
            //count++ ;
            count.incrementAndGet(); // ++
        }
        System.out.println(count);
    }

    public void run(){
        addCount();
    }

    public static void main(String[] args) {

        VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
    }

}
