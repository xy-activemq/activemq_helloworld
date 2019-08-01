package com.xywei.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicPersistent_Producer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.36.132:61616";
    public static final String TOPIC_NAME = "xy_active_demo1_topic_2";

    public static void main(String[] args) throws Exception {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        //设置持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //设置持久化之后再启动
        connection.start();
        for (int i = 1; i <= 5; i++) {

            TextMessage textMessage = session.createTextMessage("长江测试topic" + i);
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

    }


}
