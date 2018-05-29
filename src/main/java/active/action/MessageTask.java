package active.action;

import javax.jms.MapMessage;

/**
 * Created by zhangfuxin on 2017/5/23.
 */
public class MessageTask implements Runnable  {
    private MapMessage message;

    public MessageTask(MapMessage message){
        this.message = message;
    }
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "处理任务：" + this.message.getString("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
