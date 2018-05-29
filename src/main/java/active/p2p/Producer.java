package active.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * ����Ե���
 */
public class Producer {
    // 1 ���ӹ���
    private ConnectionFactory connectionFactory ;
    // 2 ���Ӷ���
    private Connection connection;
    // 3 Session����
    private Session session;
    // 4 ������
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
            MapMessage msg1 = this.session.createMapMessage();
            msg1.setString("name", "����");
            msg1.setString("age", "23");
            msg1.setStringProperty("color", "blue");
            msg1.setIntProperty("sal", 2200);
            MapMessage msg2 = this.session.createMapMessage();
            msg2.setString("name", "����");
            msg2.setString("age", "26");
            msg2.setStringProperty("color", "red");
            msg2.setIntProperty("sal", 1300);
            MapMessage msg3 = this.session.createMapMessage();
            msg3.setString("name", "����");
            msg3.setString("age", "28");
            msg3.setStringProperty("color", "green");
            msg3.setIntProperty("sal", 1500);
            MapMessage msg4 = this.session.createMapMessage();
            msg4.setString("name", "����");
            msg4.setString("age", "30");
            msg4.setStringProperty("color", "blue");
            msg4.setIntProperty("sal", 1800);




            this.messageProducer.send(destination, msg1, DeliveryMode.NON_PERSISTENT, 2, 1000*60*10L);
            this.messageProducer.send(destination, msg2, DeliveryMode.NON_PERSISTENT, 3, 1000*60*10L);
            this.messageProducer.send(destination, msg3, DeliveryMode.NON_PERSISTENT, 6, 1000*60*10L);
            this.messageProducer.send(destination, msg4, DeliveryMode.NON_PERSISTENT, 9, 1000*60*10L);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send2(/*String QueueName, Message message*/){
        try {
            //�����붩�ĵ����𡣡���
            Destination destination = this.session.createQueue("first");
            TextMessage msg = this.session.createTextMessage("����һ���ַ�������");
            this.messageProducer.send(destination, msg, DeliveryMode.NON_PERSISTENT, 9, 1000*60*10L);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        p.send1();
        //p.send2();
    }
}
