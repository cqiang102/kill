package cn.lacia.kill.business.kill.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 你是电脑
 * @create 2020/1/26 - 16:19
 */
@Configuration
public class RabbitConfiguration {
    @Bean
    public Queue queue() {
        return new Queue("email-Rabbit");
    }
}
