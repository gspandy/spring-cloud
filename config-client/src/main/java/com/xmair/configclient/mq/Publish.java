package com.xmair.configclient.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publish {

    public  static  void  main(String[] arg) throws  Exception{
        String exchange_name="test_exchange_fanout";
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
        //使用通道创建交换机，并制定交换机类型
        channel.exchangeDeclare(exchange_name,"fanout");
        String message="exchange";
        //发布消息到交换机
        channel.basicPublish(exchange_name,"",null,message.getBytes());
        channel.close();
        connection.close();

    }
}
