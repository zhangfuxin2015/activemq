package active.api;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by zhangfuxin on 2017/5/15.
 */
public class Producer {

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
        MessageProducer producer = session.createProducer(null);

        //�����������ǿ���ʹ��MessageProducer��setDeliveryMode����Ϊ�����ó־û����Ժͷǳ־û����ԣ�DeliveryMode���������Ժ���ϸ���ܡ�

        //DeliveryMode       PERSISTENT(��ǰ���͵����ݻ����ñ��浽MQ��������)   &     NON_PERSISTENT ����MQ������������ʱ�򣬻���շǳ־û������ݣ�
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //���߲����������ʹ��JMS�淶��TextMessage��ʽ�������ݣ�ͨ��Session���󣩣�����MessageProducer��send�����������ݡ�ͬ��ͻ���ʹ��receive�������н������ݡ����Ҫ���ǹر�Connection���ӡ�

        /**
         for(int i =1; i <= 5; i ++) {
         TextMessage message = session.createTextMessage();
         message.setText("���ǵ�" + i + "��Ϣ�����ݣ�");

         producer.send(destination,		//��ʾ��������ʱ��Ҫ��Ŀ�ĵز���
         message,			//��Ҫ���ݵ�����
         DeliveryMode.NON_PERSISTENT,	//��������ʱ��Ҫָ���ĳ־û���ʽ
         7,						//���ȼ� 0-9
         1000*60);				//��Ϣ�Ĺ���ʱ��
         System.out.println(message.toString());
         }*/

        MapMessage message1 = session.createMapMessage();
        message1.setInt("id", 1);
        message1.setString("name", "����");
        message1.setString("sex", "��");
        message1.setStringProperty("sex", "��");
        message1.setIntProperty("age", 25);
        producer.send(destination, message1, DeliveryMode.NON_PERSISTENT, 1, 10*1000*60);
        MapMessage message2 = session.createMapMessage();
        message2.setInt("id", 2);
        message2.setString("name", "����");
        message2.setString("sex", "Ů");
        message2.setStringProperty("sex", "Ů");
        message2.setIntProperty("age", 28);
        producer.send(destination, message2, DeliveryMode.NON_PERSISTENT, 5, 10*1000*60);
        MapMessage message3 = session.createMapMessage();
        message3.setInt("id", 3);
        message3.setString("name", "����");
        message3.setString("sex", "Ů");
        message3.setStringProperty("sex", "Ů");
        message3.setIntProperty("age", 16);
        producer.send(destination, message3, DeliveryMode.NON_PERSISTENT, 9, 10*1000*60);


        //�������session��ʱ�򣬿���ָ����һ������ΪTrue��
        // ��ʾ����MQ������ �ͱ���Ҫ���������ݷ��ͳɹ��Ժ����session.commit()������
        //session.commit();

        if(connection != null){
            connection.close();
        }


    }
}
