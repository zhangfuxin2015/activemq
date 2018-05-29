package Thread.Thread06;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zhangfuxin on 2017/5/15.
 */
public class MyRejected implements RejectedExecutionHandler {
    public MyRejected(){
    }

    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义处理..");
        System.out.println("当前被拒绝任务为：" + r.toString());

    }
}
