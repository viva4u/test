package com.test;

import com.MQ.JMSConsumer;
import com.MQ.JMSProducer;
import org.junit.Test;

public class testMQ {
    @Test
    public void showTestMQ() throws Exception{
        JMSProducer jmsProducer = new JMSProducer();
        jmsProducer.Producer();
    }
    @Test
    public void JMSConsumerTest() throws Exception{
        JMSConsumer consumer = new JMSConsumer();
        consumer.Consumer();
    }
}
