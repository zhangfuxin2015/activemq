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
         * ��ʹ���н����ʱ�������µ�������Ҫִ�У�����̳߳�ʵ���߳���С��corePoolSize�������ȴ����̣߳�
         * ������corePoolSize����Ὣ���������У�
         * �������������������߳���������maximumPoolSize��ǰ���£������µ��̣߳�
         * ���߳�������maximumPoolSize����ִ�оܾ����ԡ��������Զ��巽ʽ��
         *
         */

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 				//coreSize   ��ʼ�����߳�
                2, 				//MaxSize  ����߳�
                60, 			//60
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)			//ָ��һ�ֶ��� ���н���У�
                //new LinkedBlockingQueue<Runnable>()
                //�ܾ����ԣ�������jdk�ṩ�ļ�������
                //AbortPolicy��ֱ���׳��쳣��֯ϵͳ��������
                //CallerRunsPolicy��ֻҪ�̳߳�δ�رգ��ò���ֱ���ڵ������߳��У����е�ǰ������������
                //DiscardOldestPolicy���������ϵ�һ�����󣬳����ٴ��ύ��ǰ����
                //DiscardPolicy�������޷���������񣬲������κδ���
                //�����Ҫ�Զ���ܾ����Կ���ʵ��RejectedExecutionHandler�ӿڡ�
                ,new MyRejected()
        );
        //���ύmt1ʱ����պ����̣߳�Ȼ�� mt2��mt3,mt4����뵽�н�������档 ��ִ��mt5��ʱ��
        //���ֶ������ˣ���� ��������̣߳� ��ִ��mt6��ʱ������׳��쳣��

        MyTask mt1 = new MyTask(1, "����1");
        MyTask mt2 = new MyTask(2, "����2");
        MyTask mt3 = new MyTask(3, "����3");
        MyTask mt4 = new MyTask(4, "����4");
        MyTask mt5 = new MyTask(5, "����5");
        MyTask mt6 = new MyTask(6, "����6");

        pool.execute(mt1);
        pool.execute(mt2);
        pool.execute(mt3);
        pool.execute(mt4);
        pool.execute(mt5);
        pool.execute(mt6);

        pool.shutdown();

    }
}
