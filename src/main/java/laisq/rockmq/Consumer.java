package laisq.rockmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import java.util.List;


public class Consumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer");
        //consumer.setNamesrvAddr("192.168.1.26:9876");
        consumer.setNamesrvAddr("192.168.232.112:9876");
        
        try {
            // ����PushTopic��TagΪpush����Ϣ
            consumer.subscribe("PushTopic", "push");
            // �����һ����������Ϣ����ͷȡ����
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                        ConsumeConcurrentlyContext Context) {
                    /*
                     * Message msg = list.get(0);
                     * System.out.println(msg.toString());
                     */
                    MessageExt messageExt = list.get(0);
                    System.out.println("msg:\t" + new String(messageExt.getBody()));
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}