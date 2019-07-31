package com.xywei.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Consumer {

    public static final String ACTIVE_URI = "tcp://192.168.36.132:61616";
    public static final String QUEUE_NAME = "xy_demo1_3";

    public static void main(String[] args) {

        try {
            //一样的套路类似JDBC
            //1st
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_URI);
            //2nd
            Connection connection = activeMQConnectionFactory.createConnection();
            //总是忘记写
            connection.start();
            //3rd
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //4th
            Queue queue = session.createQueue(QUEUE_NAME);

            MessageConsumer consumer = session.createConsumer(queue);

            while (true) {

                TextMessage textMessage = (TextMessage) consumer.receive();
                if (textMessage != null) {
                    String text = textMessage.getText();
                    System.out.println("text = [" + text + "]");
                } else {
                    break;
                }
            }
            consumer.close();
            session.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("消费者运行完毕");
        }


    }

}
