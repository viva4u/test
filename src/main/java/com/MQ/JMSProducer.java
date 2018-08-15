package com.MQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.omg.PortableInterceptor.ACTIVE;

import javax.jms.*;

public class JMSProducer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public void Producer() throws Exception{
        ConnectionFactory factory = new ActiveMQConnectionFactory(JMSProducer.USERNAME,JMSProducer.PASSWORD,JMSProducer.URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("yanxjtest");
        MessageProducer producer = session.createProducer(destination);
        for(int i =0 ;i<10;i++){
            TextMessage msg = session.createTextMessage("this is yanxj msg"+i);
            Thread.sleep(500);
            producer.send(msg);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
