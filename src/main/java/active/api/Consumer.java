package active.api;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by zhangfuxin on 2017/5/15.
 */
public class Consumer {
    public static void main(String[] args) throws Exception {

        //��һ��������ConnectionFactory����������Ҫ�����û��������롢�Լ�Ҫ���ӵĵ�ַ����ʹ��Ĭ�ϼ��ɣ�Ĭ�϶˿�Ϊ"tcp://localhost:61616"
        ConnectionFactory conntionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );

        //�ڶ�����ͨ��ConnectionFactory�����������Ǵ���һ��Connection���ӣ����ҵ���Connection��start�����������ӣ�ConnectionĬ���ǹرյġ�
        Connection connection = conntionFactory.createConnection();
        connection.start();

        //��������ͨ��Connection���󴴽�Session�Ự�������Ļ������󣩣����ڽ�����Ϣ����������1Ϊ�Ƿ����������񣬲�������2Ϊǩ��ģʽ��һ�����������Զ�ǩ�ա�


        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        //���Ĳ���ͨ��Session����Destination����ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ�Ķ�����PTPģʽ�У�Destination������Queue�����У���Pub/Subģʽ��Destination������Topic�����⡣�ڳ����п���ʹ�ö��Queue��Topic��
        Destination destination = session.createQueue("first");

        //���岽��������Ҫͨ��Session���󴴽���Ϣ�ķ��ͺͽ��ն��������ߺ������ߣ�MessageProducer/MessageConsumer��
        //��ӹ�������
        String selector = "sex = 'Ů' AND age < 20";
        MessageConsumer consumer = session.createConsumer(destination, selector);

        //��������������Ϣ
        while(true){
            /**
             TextMessage message = (TextMessage)consumer.receive();
             //���session�����ĵڶ������� �������ΪSession.CLIENT_ACKNOWLEDGEʱ����ô��Ϣ�Ľ��նˣ�Consumer���ͱ��������ǩ�յķ������Ӷ���������
             //Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
             //message.acknowledge(); 	//��ʾ������MQ������ ���Ѿ�ǩ�����������

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
