package Thread.Thread01;

/**
 * synchronized����
 */
public class MyThread extends  Thread {
    private int count = 5 ;

    //synchronized����
    public synchronized void run(){
        count--;
        System.out.println(this.currentThread().getName() + " count = "+ count);
    }

    public static void main(String[] args) {
        /**
         * ������������̷߳���myThread��run����ʱ�����Ŷӵķ�ʽ���д��������Ŷ��ǰ���CPU������Ⱥ�˳������ģ���
         * 		һ���߳���Ҫִ��synchronized���εķ�����Ĵ��룺
         * 		1 ���Ի����
         * 		2 ����õ�����ִ��synchronized���������ݣ��ò�����������߳̾ͻ᲻�ϵĳ��Ի���������ֱ���õ�Ϊֹ��
         * 		   �����Ƕ���߳�ͬʱȥ�������������Ҳ���ǻ��������������⣩
         */
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread,"t1");
        Thread t2 = new Thread(myThread,"t2");
        Thread t3 = new Thread(myThread,"t3");
        Thread t4 = new Thread(myThread,"t4");
        Thread t5 = new Thread(myThread,"t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
