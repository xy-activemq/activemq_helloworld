package com.xywei.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    public static final String ACTIVE_URI = "tcp://192.168.36.132:61616";
    public static final String QUEUE_NAME = "xy_demo1_1";

    public static void main(String[] args) {

        try {
            //一样的套路类似JDBC
            //1st
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_URI);
            //2nd
            Connection connection = activeMQConnectionFactory.createConnection();
            //一定不能忘记
            connection.start();
            //3rd 之前出错，写了true
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //4th
            Queue queue = session.createQueue(QUEUE_NAME);

            //5th创建生成者
            MessageProducer producer = session.createProducer(queue);

            //6th创建消息
            for (int i = 1; i <= 10; i++) {
                TextMessage textMessage = session.createTextMessage("helloworld，我是第" + i + "条消息!");
                producer.send(textMessage);
            }

            //如果transacted是true，需要加入
//            session.commit();
            producer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            System.out.println("运行完毕");
        }


    }

}
