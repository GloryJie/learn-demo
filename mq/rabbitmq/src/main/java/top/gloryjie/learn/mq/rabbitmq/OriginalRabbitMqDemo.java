package top.gloryjie.learn.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jie
 * @since 2019/12/16
 */
public class OriginalRabbitMqDemo {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("jojo", "topic");

            String routingKey = "jojo";
            String message = "jojo-msg";

            channel.basicPublish("jojo", routingKey, null, message.getBytes("UTF-8"));

            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }

    }
}
