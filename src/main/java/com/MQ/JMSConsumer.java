package com.MQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSConsumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public void Consumer() throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME,JMSConsumer.PASSWORD,JMSConsumer.URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("yanxjtest");
        MessageConsumer consumer = session.createConsumer(destination);
        while(true){
            TextMessage msg = (TextMessage) consumer.receive(100000);
            if(msg!=null){
                System.out.println("consumer message is "+msg.getText());
            }else break;
        }
        session.commit();
//        session.close();
//        connection.close();
    }
}
