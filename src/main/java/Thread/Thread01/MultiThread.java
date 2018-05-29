package Thread.Thread01;

/**
 *多个线程调用N个对象的方法，由于普通的synchronized 是加锁在 对象上，如果创建多个对象，多个线程去调用，需要把方法变为static，在类上面加锁
 */
public class MultiThread {
    private static  int num = 0;

    /** static */
    public static  synchronized void printNum(String tag){
        try {

            if(tag.equals("a")){
                num = 100;
                System.out.println("tag a, set num over!");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b, set num over!");
            }

            System.out.println("tag " + tag + ", num = " + num);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //注意观察run方法输出顺序
    public static void main(String[] args) {

        //俩个不同的对象
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                m1.printNum("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                m2.printNum("b");
            }
        });

        t1.start();
        t2.start();

    }
}
