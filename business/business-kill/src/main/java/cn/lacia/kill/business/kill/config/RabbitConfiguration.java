package cn.lacia.kill.business.kill.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 你是电脑
 * @create 2020/1/26 - 16:19
 */
@Configuration
@Slf4j
public class RabbitConfiguration {


    @Bean
    public Queue queue() {
        return new Queue("email-Rabbit",true);
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue successKillDeadQueue() {
        Map<String,Object> map = new HashMap<String, Object>(){{
            put("x-dead-letter-exchange","successKillDeadExchange");
            put("x-dead-letter-routing-key","successKillDeadQueue-key");
            put("x-message-ttl",3*60*1000);
        }};
        return new Queue("successKillDead",true,false,false,map);
    }

    /**
     * 基本交换机
     * @return
     */
    @Bean
    public TopicExchange successKillDeadProdExchange(){
        return new TopicExchange("successKillDeadProdExchange",true,false);
    }

    /**
     * 创建基本交换机+基本路由 -> 死信队列 绑定
     * @return
     */
    @Bean
    public Binding successKillDeadProdBinding(){
        return BindingBuilder.bind(successKillDeadQueue()).to(successKillDeadProdExchange()).with("prod-routing-key");
    }


    /**
     * 真正队列
     * @return
     */
    @Bean
    public Queue successKillRealQueue(){
        return new Queue("successKillRealQueue",true);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public TopicExchange successKillDeadExchange(){
        return new TopicExchange("successKillDeadExchange",true,false);
    }

    /**
     * 死信交换机+死信路由->真正的队列
     * @return
     */
    @Bean
    public Binding successKillDeadBinding(){
        return BindingBuilder.bind(successKillRealQueue()).to(successKillDeadExchange()).with("successKillDeadQueue-key");
    }


}
