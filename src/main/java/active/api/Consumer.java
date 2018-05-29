package active.api;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by zhangfuxin on 2017/5/15.
 */
public class Consumer {
    public static void main(String[] args) throws Exception {

        //第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码、以及要连接的地址，均使用默认即可，默认端口为"tcp://localhost:61616"
        ConnectionFactory conntionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        //第二步：通过ConnectionFactory工厂对象我们创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的。
        Connection connection = conntionFactory.createConnection();
        connection.start();

        //第三步：通过Connection对象创建Session会话（上下文环境对象），用于接收消息，参数配置1为是否启用是事务，参数配置2为签收模式，一般我们设置自动签收。


        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        //第四步：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，在PTP模式中，Destination被称作Queue即队列；在Pub/Sub模式，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic。
        Destination destination = session.createQueue("first");

        //第五步：我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者）MessageProducer/MessageConsumer。
        //添加过滤器：
        String selector = "sex = '女' AND age < 20";
        MessageConsumer consumer = session.createConsumer(destination, selector);

        //第六步：接受消息
        while(true){
            /**
             TextMessage message = (TextMessage)consumer.receive();
             //如果session创建的第二个参数 如果配置为Session.CLIENT_ACKNOWLEDGE时，那么消息的接收端（Consumer）就必须调用其签收的方法，从而接收数据
             //Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
             //message.acknowledge(); 	//表示，告诉MQ服务器 我已经签收了这个数据

             if(message == null) break;

             String ret = message.getText();
             System.out.println(ret);
             */
            Message messge = consumer.receive();
            if(messge instanceof TextMessage){
                //
            }
            if(messge instanceof MapMessage){
                //
                MapMessage mapMessage = (MapMessage)messge;
                System.out.println(mapMessage.getString("name"));
                System.out.println(mapMessage.toString());
            }

        }






    }
}
