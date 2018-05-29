package active.publish;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
/**
 * 订阅消息
 */
public class Consumer2 {
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    public Consumer2(){
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
                    System.out.println("c2收到消息：------------");
                    TextMessage m = (TextMessage)message;
                    System.out.println(m.getText());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Consumer2 c2 = new Consumer2();
        c2.receive();
    }

}
