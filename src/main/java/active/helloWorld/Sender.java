package active.helloWorld;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
/**
 * Created by zhangfuxin on 2017/5/15.
 */
public class Sender {
    public static void main(String[] args)throws Exception {

        //第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码、以及要连接的地址，
        // 均使用默认即可，默认端口为"tcp://localhost:61616"
        ConnectionFactory conntionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        //第二步：通过ConnectionFactory工厂对象我们创建一个Connection连接，
        // 并且调用Connection的start方法开启连接，Connection默认是关闭的。
        Connection connection = conntionFactory.createConnection();
        connection.start();

        //第三步：通过Connection对象创建Session会话（上下文环境对象），用于接收消息，
        // 参数配置1为是否启用是事务，参数配置2为签收模式，一般我们设置自动签收。
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        //第四步：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，
        // 在PTP(一对一)模式中，Destination被称作Queue即队列；在Pub/Sub模式，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic。
        Destination destination = session.createQueue("first");

        //第五步：我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者）MessageProducer/MessageConsumer。
        MessageProducer producer = session.createProducer(destination);

        //第六步：我们可以使用MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性（DeliveryMode）
        // ，我们稍后详细介绍。
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //第七步：最后我们使用JMS规范的TextMessage形式创建数据（通过Session对象），并用MessageProducer的send方法发送数据。
        // 同理客户端使用receive方法进行接收数据。最后不要忘记关闭Connection连接。
        for(int i =1; i <= 5; i ++) {
            TextMessage message = session.createTextMessage();
            message.setText("我是第" + i + "消息的内容！");
            producer.send(message);
            System.out.println(message.toString());
        }

        if(connection != null){
            connection.close();
        }
        //http://localhost:8161/admin/ 访问这个地址，

    }
}
