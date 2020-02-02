package cn.lacia.kill.business.kill.config;

import cn.lacia.kill.business.kill.service.ItemKillSuccessService;
import cn.lacia.kill.commons.domain.ItemKillSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 你是电脑
 * @create 2020/1/31 - 16:53
 */
@Component
@Slf4j
@RabbitListener(queues = "successKillRealQueue")
public class RabbitConsumer {
    @Resource
    private ItemKillSuccessService itemKillSuccessService;
    @RabbitHandler
    public void consumerDead(ItemKillSuccess itemKillSuccess){

        itemKillSuccess = itemKillSuccessService.selectItemSuccessByCodeAndStatusIsZero(itemKillSuccess.getCode());
        log.info("订单状态 -> {}",itemKillSuccess);
        // 秒杀成功超时未付款
        if (itemKillSuccess != null && itemKillSuccess.getStatus() == 0){
            // 订单超时
            log.info("{} 订单失效",itemKillSuccess.getCode());
            itemKillSuccessService.updateStatusByCode(itemKillSuccess.getCode(), (byte) -1);
        }
    }

    @Scheduled(cron = "0/10 * * * * ? ")
    public void scheduledExpireOrders(){
        List<ItemKillSuccess> itemKillSuccessList = itemKillSuccessService.selectStatusIsZeroAll();
        if (! itemKillSuccessList.isEmpty()) {
            itemKillSuccessList.forEach(itemKillSuccess -> {
                if (itemKillSuccess.getGap() >= 30){
                    itemKillSuccessService.updateStatusByCode(itemKillSuccess.getCode(), (byte) -1);
                    log.info("{} 订单失效",itemKillSuccess.getCode());
                }
            });
        }
    }
}
