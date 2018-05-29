package active.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
/**
 * Created by zhangfuxin on 2017/5/23.
 */
public class Consumer {

    public final String SELECTOR_1 = "color = 'blue'";
    //"JMSType = 'car' AND weight > 2500");
    public final String SELECTOR_2 = "color = 'blue' AND sal > 2000";

    public final String SELECTOR_3 = "receiver = 'A'";

    //public final String SELECTOR_4 = "receiver = 'B'";

    // 1 ���ӹ���
    private ConnectionFactory connectionFactory ;
    // 2 ���Ӷ���
    private Connection connection;
    // 3 Session����
    private Session session;
    // 4 ������
    private MessageConsumer messageConsumer;
    // 5 Ŀ���ַ
    private Destination destination;

    public Consumer(){
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnectionFactory.DEFAULT_USER,
                    ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    "tcp://localhost:61616");
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            this.destination = this.session.createQueue("first");
            this.messageConsumer = this.session.createConsumer(this.destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receiver(){
        try {
            this.messageConsumer.setMessageListener(new Listener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    class Listener implements MessageListener {
        public void onMessage(Message message) {
            try {

                if(message instanceof TextMessage){

                }
                if(message instanceof MapMessage){
                    MapMessage ret = (MapMessage)message;
                    System.out.println(ret.toString());
                    System.out.println(ret.getString("name"));
                    System.out.println(ret.getString("age"));
                }
                //....

            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }



    public static void main(String[] args) {

        Consumer c = new Consumer();
        c.receiver();

    }
}
