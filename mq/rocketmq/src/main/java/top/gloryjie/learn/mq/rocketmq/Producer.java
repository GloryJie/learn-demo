package top.gloryjie.learn.mq.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @author Jie
 * @since 2020/7/4
 */
public class Producer {

    public static void main(String[] args) {
        DefaultMQProducer mqProducer = new DefaultMQProducer("PID_JOJO");
        mqProducer.setNamesrvAddr("127.0.0.1:9876");
        try {
            // 启动
            mqProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        Message message = new Message();
        message.setTopic("JOJO");
        message.setBody("Crazy Diamond".getBytes(StandardCharsets.UTF_8));

        // 同步发送
        try {
            SendResult sendResult = mqProducer.send(message);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }

        // 异步发送
        try {
            mqProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("msg send success, result: " + sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.err.println("msg send fail ");
                }
            });
        } catch (MQClientException | RemotingException | InterruptedException e) {
            e.printStackTrace();
        }

        // oneWay单向发送
        try {
            mqProducer.sendOneway(message);
        } catch (MQClientException | InterruptedException | RemotingException e) {
            e.printStackTrace();
        }
    }

}
