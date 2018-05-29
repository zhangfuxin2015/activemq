package active.publish;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by zhangfuxin on 2017/5/23.
 */
public class Consumer1 {
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    public Consumer1(){
        try {
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    "tcp://localhost:61616");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive() throws Exception{
        Destination destination = session.createTopic("topic1");
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(new Listener());
    }

    class Listener implements MessageListener {
        public void onMessage(Message message) {
            try {
                if(message instanceof TextMessage){
                    System.out.println("c1收到消息：------------");
                    TextMessage m = (TextMessage)message;
                    System.out.println(m.getText());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Consumer1 c1 = new Consumer1();
        c1.receive();
    }
}
