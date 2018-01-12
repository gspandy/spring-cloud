package com.xmair.configclient.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitCustomer {

    private final static String QUEUE_NAME = "rabbitMQ.test";

    public static void main(String[] args) throws Exception, IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("localhost");
        factory.setUsername("06645");
        factory.setPassword("06645");
        factory.setPort(5672);
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.basicQos(1);
        System.out.println("Customer Waiting Received messages");
        //定义队列的消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer  );
        while (true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println(message);
            Thread.sleep(10  );
            //返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

        }

    }

}
