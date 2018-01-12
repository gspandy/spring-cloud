package com.xmair.configclient.mq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Subscribe {

    public  static  void  main(String[] arg) throws  Exception{
        String exchange_name="test_exchange_direct";
        String QUEUE_NAME="test_queue_exchange1";
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
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,exchange_name,"key1");
        channel.basicQos(1);//同意时刻服务器只会发一条消息给消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,false,consumer);
        while (true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println(message);
            System.out.println(delivery.getEnvelope().getDeliveryTag());
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

        }
    }
}
