package active.api;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by zhangfuxin on 2017/5/15.
 */
public class Producer {

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
        MessageProducer producer = session.createProducer(null);

        //第六步：我们可以使用MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性（DeliveryMode），我们稍后详细介绍。

        //DeliveryMode       PERSISTENT(当前发送的数据会永久保存到MQ服务器上)   &     NON_PERSISTENT （在MQ服务器重启的时候，会清空非持久化的数据）
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //第七步：最后我们使用JMS规范的TextMessage形式创建数据（通过Session对象），并用MessageProducer的send方法发送数据。同理客户端使用receive方法进行接收数据。最后不要忘记关闭Connection连接。

        /**
         for(int i =1; i <= 5; i ++) {
         TextMessage message = session.createTextMessage();
         message.setText("我是第" + i + "消息的内容！");

         producer.send(destination,		//表示发送数据时需要的目的地参数
         message,			//需要传递的数据
         DeliveryMode.NON_PERSISTENT,	//发送数据时需要指定的持久化方式
         7,						//优先级 0-9
         1000*60);				//消息的过期时间
         System.out.println(message.toString());
         }*/

        MapMessage message1 = session.createMapMessage();
        message1.setInt("id", 1);
        message1.setString("name", "张三");
        message1.setString("sex", "男");
        message1.setStringProperty("sex", "男");
        message1.setIntProperty("age", 25);
        producer.send(destination, message1, DeliveryMode.NON_PERSISTENT, 1, 10*1000*60);
        MapMessage message2 = session.createMapMessage();
        message2.setInt("id", 2);
        message2.setString("name", "李四");
        message2.setString("sex", "女");
        message2.setStringProperty("sex", "女");
        message2.setIntProperty("age", 28);
        producer.send(destination, message2, DeliveryMode.NON_PERSISTENT, 5, 10*1000*60);
        MapMessage message3 = session.createMapMessage();
        message3.setInt("id", 3);
        message3.setString("name", "王五");
        message3.setString("sex", "女");
        message3.setStringProperty("sex", "女");
        message3.setIntProperty("age", 16);
        producer.send(destination, message3, DeliveryMode.NON_PERSISTENT, 9, 10*1000*60);


        //如果创建session的时候，可以指定第一个参数为True。
        // 表示开启MQ的事务 就必须要在批量数据发送成功以后调用session.commit()方法。
        //session.commit();

        if(connection != null){
            connection.close();
        }


    }
}
