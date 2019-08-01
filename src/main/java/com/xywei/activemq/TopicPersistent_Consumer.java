package com.xywei.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicPersistent_Consumer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.36.132:61616";
    public static final String TOPIC_NAME = "xy_active_demo1_topic_2";


    public static void main(String[] args) throws Exception {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
//        connection.setClientID("长江001");
        connection.setClientID("长江002");
//        connection.setClientID("长江003");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "长江号");
        connection.start();

//        topicSubscriber.setMessageListener((message -> {
//            if ((null != message)){
//                try {
//                    TextMessage textMessage= (TextMessage) topicSubscriber.receive();
//                    System.out.println("message = [" + textMessage.getText() + "]");
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        }));
        Message receiveMessage = topicSubscriber.receive();
        while (null != receiveMessage) {

            TextMessage textMessage = (TextMessage) receiveMessage;
            System.out.println("textMessage = [" + textMessage.getText() + "]");
            receiveMessage=topicSubscriber.receive(3000);
        }


        topicSubscriber.close();
        session.close();
        connection.close();
    }

}
