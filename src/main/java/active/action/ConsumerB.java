package active.action;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.*;
/**
 * Created by zhangfuxin on 2017/5/23.
 */
public class ConsumerB {
    public final String SELECTOR = "receiver = 'B'";

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

    public ConsumerB(){
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnectionFactory.DEFAULT_USER,
                    ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                    "tcp://localhost:61616");
            this.connection = this.connectionFactory.createConnection();
            this.connection.start();
            this.session = this.connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            this.destination = this.session.createQueue("first");
            this.messageConsumer = this.session.createConsumer(this.destination,SELECTOR);
            System.out.println("Consumer B start..");
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

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10000);
        //new LinkedBlockingQueue<Runnable>();
        ExecutorService executor  = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                20,
                120L,
                TimeUnit.SECONDS,
                queue);

        public void onMessage(Message message) {
            try {
                if(message instanceof MapMessage){
                    MapMessage ret = (MapMessage)message;

//					Thread.sleep(500);
//					System.out.println("��������:" + ret.getString("id"));
                    executor.execute(new MessageTask(ret));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {

        ConsumerB c = new ConsumerB();
        c.receiver();

    }
}
