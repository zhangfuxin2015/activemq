package Thread.Thread02;

/**
 *ʾ���ܽ᣺
 ��java�У�ÿһ���̶߳�����һ�鹤���ڴ��������д���������̹߳�������ڴ��еı���ֵ�Ŀ��������߳�ִ��ʱ�������Լ��Ĺ����ڴ����в�����Щ������Ϊ�˴�ȡһ������ı�����һ���߳�ͨ���Ȼ�ȡ������ȥ��������ڴ湤����������Щ��������������̵߳Ĺ����ڴ�������ȷ��װ�뵽���Լ����ڵĹ����ڴ����У����߳̽���ʱ��֤�ù����ڴ����б�����ֵд�ص������ڴ��С�
 һ���߳̿���ִ�еĲ�����ʹ�ã�use������ֵ��assign����װ�أ�load�����洢��store����������lock����������unlock����
 �����ڴ����ִ�еĲ����ж���read����д��write����������lock����������unlock����ÿ����������ԭ�ӵġ�
 volatile�����þ���ǿ���̵߳����ڴ棨�����ڴ棩��ȥ��ȡ����������ȥ�̹߳����ڴ�����ȥ��ȡ���Ӷ�ʵ���˶���̼߳�ı����ɼ���Ҳ���������̰߳�ȫ�Ŀɼ��ԡ�

 */
public class RunThread extends Thread {
    //volatile
    private volatile boolean isRunning = true;

    private void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void run(){
        System.out.println("����run����..");
        while(isRunning == true){
            //..
        }
        System.out.println("�߳�ֹͣ");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
        rt.start();
        Thread.sleep(3000);
        rt.setRunning(false);
        System.out.println("isRunning��ֵ�Ѿ���������false");
        Thread.sleep(1000);
        System.out.println(rt.isRunning);
    }

}
