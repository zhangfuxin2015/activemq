package Thread.Thread01;

/**
 * Created by zhangfuxin on 2017/5/11.
 */
public class DirtyRead {
    private String username = "z3";
    private String password = "123";

    public synchronized void setValue(String username, String password){
        this.username = username;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.password = password;

        System.out.println("setValue最终结果：username = " + username + " , password = " + password);
    }

    public synchronized void getValue(){
        System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
    }


    public static void main(String[] args) throws Exception{

        final DirtyRead dr = new DirtyRead();
        //dr锁  锁住  不让 dr.getvalue 进行调用，除非 setvalue完毕
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                dr.setValue("w5", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);

        dr.getValue();
    }


}
