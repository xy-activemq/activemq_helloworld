package com.xywei.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Topic_Consumer {


    public static final String ACTIVEMQ_URL = "tcp://192.168.36.132:61616";
    public static final String TOPIC_NAME = "xy_active_demo1_topic_1";

    public static void main(String[] args) throws Exception {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

//        Queue queue = session.createQueue(QUEUE_NAME);

        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(topic);

//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                if (null != message && message instanceof TextMessage){
//                    System.out.println("message = [" + message + "]");
//                }
//            }
//        });
        //玩一下lambada
        consumer.setMessageListener((message -> {
            if (null != message && message instanceof TextMessage) {
                System.out.println("1号message = [" + message + "]");
            }
        }));

        //为什么我按键了没起到作用？
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }


}
