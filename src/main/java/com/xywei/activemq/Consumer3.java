package com.xywei.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 使用监听器方式
 */
public class Consumer3 {

    public static final String ACTIVEMQ_URL = "tcp://192.168.36.132:61616";
    public static final String QUEUE_NAME = "xy_active_demo1_3";

    public static void main(String[] args) throws Exception {

        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage){
                    System.out.println("message = [" + message + "]");
                }
            }
        });
        //为什么我按键了没起到作用？
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }


}
