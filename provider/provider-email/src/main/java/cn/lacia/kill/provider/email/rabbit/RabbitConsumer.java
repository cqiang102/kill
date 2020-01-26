package cn.lacia.kill.provider.email.rabbit;

import cn.lacia.kill.commons.domain.ItemKillSuccess;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 你是电脑
 * @create 2020/1/26 - 16:25
 */
@Component
@RabbitListener(queues = "email-Rabbit")
public class RabbitConsumer {
    @RabbitHandler
    public void process(ItemKillSuccess itemKillSuccess) {
        System.out.println("Consumer: " + itemKillSuccess);
    }
}
