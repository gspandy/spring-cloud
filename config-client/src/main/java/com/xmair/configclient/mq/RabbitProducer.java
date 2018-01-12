package com.xmair.configclient.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitProducer {

    public final static String QUEUE_NAME="rabbitMQ.test";

    public static void main(String[] args) throws Exception,IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");
        factory.setUsername("06645");
        factory.setPassword("06645");
        factory.setPort(5672);
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //  声明一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        for(int i=0;i<100;i++){//发送消息到队列中
            String message = "Hello RabbitMQ"+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes("utf-8"));
            Thread.sleep(i*10);
        }

        //关闭通道和连接
        channel.close();
        connection.close();
    }

}
