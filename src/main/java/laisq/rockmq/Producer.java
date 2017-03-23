/*
 * 文件名：Producer.java
 * 版权：深圳柚安米科技有限公司版权所有
 * 修改人：laishaoqiang
 * 修改时间：2016年12月5日
 * 修改内容：新增
 */
package laisq.rockmq;

/**
 * Created by yyp on 15-12-18.
 */

import com.alibaba.rocketmq.common.message.Message;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;


public class Producer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        //producer.setNamesrvAddr("192.168.1.26:9876");
        producer.setNamesrvAddr("192.168.232.112:9876");
        
        try {
            producer.start();
            int i = 0;

            while (true) {
                i++;
                Message msg = new Message("PushTopic", "push", "1", ("Just for yyp" + i).getBytes());
                SendResult result = producer.send(msg);

                /*
                 * msg = new Message("PushTopic", "push", "2",
                 * "Just for test.".getBytes());
                 * 
                 * result = producer.send(msg); System.out.println("id:" +
                 * result.getMsgId() + " result:" + result.getSendStatus());
                 */

                msg = new Message("PullTopic", "pull", "1", "Just for test112.".getBytes());
                result = producer.send(msg);
                System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus() + " key:" + msg.getKeys());
                if (i % 10 == 0) {
                    Thread.sleep(20000);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // producer.shutdown();
        }
    }
}