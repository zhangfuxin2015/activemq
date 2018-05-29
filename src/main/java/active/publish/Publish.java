package active.publish;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
/**
 * 订阅与发布
 */
public class Publish {
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    public Publish(){
        try {
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    "tcp://localhost:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() throws Exception{
        //这里创建一个 topic
        Destination destination = session.createTopic("topic1");
        TextMessage textMassge = session.createTextMessage("我是内容");
        producer.send(destination, textMassge);
    }

    public static void main(String[] args) throws Exception{
        Publish p = new Publish();
        p.sendMessage();
    }
}
