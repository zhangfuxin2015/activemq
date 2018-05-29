package active.action;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
/**
 * Created by zhangfuxin on 2017/5/23.
 */
public class Producer {

    // 1 连接工厂
    private ConnectionFactory connectionFactory ;
    // 2 连接对象
    private Connection connection;
    // 3 Session对象
    private Session session;
    // 4 生产者
    private MessageProducer messageProducer;

    public Producer(){
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnectionFactory.DEFAULT_USER,
                    ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    "tcp://localhost:61616");
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            this.messageProducer = this.session.createProducer(null);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public Session getSession(){
        return this.session;
    }

    public void send1(/*String QueueName, Message message*/){
        try {
            Destination destination = this.session.createQueue("first");
            for(int i = 0 ; i < 100; i ++){
                MapMessage msg = this.session.createMapMessage();
                int id = i;
                msg.setInt("id", id);
                msg.setString("name", "张" + i);
                msg.setString("age", "" + i);
                String receiver = id%2 == 0 ? "A" : "B";
                msg.setStringProperty("receiver", receiver);
                                                                                        ////优先级 0-9
                this.messageProducer.send(destination, msg, DeliveryMode.NON_PERSISTENT, 2, 1000*60*10L);
                System.out.println("message send id : " + id);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        p.send1();
    }
}
