package Thread.Thread03;

import java.util.Vector;

/**
 *ͬ�������������̰߳�ȫ�ģ�����ĳЩ�����¿�����Ҫ�������������ϲ�����
 * ����������磺��������������Ԫ�أ����������������е�Ԫ�أ�����ת������ָ����˳���ҵ���ǰԪ�ص���һ��Ԫ�أ���
 * �Լ��������㡣��Щ���ϲ����ڶ��̲߳������޸�����ʱ�����ܻ���ֳ��������Ϊ�����ı���ConcurrentModificationException��
 * ԭ���ǵ����������Ĺ����У����������޸������ݣ������������ڵ�������Ƶ�ʱ��û�п��ǲ����޸ĵ����⡣
 ͬ��������������ϵ�Vector��HashTable����Щ������ͬ��������ʵ������JDK��Collections.synchronized***�ȹ�������ȥ����ʵ�ֵġ�
 ��ײ�Ļ����޷Ǿ����ô�ͳ��synchronized�ؼ��ֶ�ÿ�����õķ���������ͬ����ʹ��ÿ��ֻ����һ���̷߳���������״̬��
 ������Բ��������ǽ��컥����ʱ���߲����������ڱ�֤�̰߳�ȫ��ͬʱ��Ҳ����Ҫ���㹻�õ����ܡ�
 */
public class Tickets {
    public static void main(String[] args) {
        //��ʼ����Ʊ�ز���ӻ�Ʊ:�����߳�ͬ���ɲ���Vector���ArrayList  HashTable���HashMap

        final Vector<String> tickets = new Vector<String>();
        for(int i = 1; i<= 1000; i++){
            tickets.add("��Ʊ"+i);
        }

        for(int i = 1; i <=10; i ++){
            //
            new Thread("�߳�"+i){
                public void run(){
                    //iterator...
                }
            }.start();

            new Thread("�߳�"+i){
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
