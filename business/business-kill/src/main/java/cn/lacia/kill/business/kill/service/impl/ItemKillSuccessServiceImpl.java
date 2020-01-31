package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.business.kill.mapper.ItemKillSuccessMapper;
import cn.lacia.kill.business.kill.service.ItemKillSuccessService;
import cn.lacia.kill.commons.domain.ItemKillSuccess;
import cn.lacia.kill.commons.utils.SnowFlake;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Service
public class ItemKillSuccessServiceImpl implements ItemKillSuccessService{

    @Resource
    private ItemKillSuccessMapper itemKillSuccessMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public boolean insert(ItemKill kill, String userId){
        ItemKillSuccess build = ItemKillSuccess.builder()
                .killId(kill.getId())
                .userId(userId)
                .code(snowFlake.nextId()+"")
                .createTime(new Date())
                .status((byte) 0)
                .itemId(kill.getItemId())
                .build();
        int insert = itemKillSuccessMapper.insert(build);
        if (insert>0){
            // TODO 发送邮件通知用户
            amqpTemplate.convertAndSend("email-Rabbit", build);
            amqpTemplate.convertAndSend("successKillDeadProdExchange",
                    "prod-routing-key", build);
            // 发送秒杀成功后超时订单的消息
            return true;
        }
        return false;


    }

    @Override
    public ItemKillSuccess selectItemSuccessByCode(String code) {
        return itemKillSuccessMapper.selectOne(ItemKillSuccess.builder().code(code).status((byte) 0).build());
    }

    @Override
    public void updateStatusByCode(String code, byte i) {
        Example example = new Example(ItemKillSuccess.class);
        example.createCriteria().andEqualTo("code",code).andEqualTo("status",0);
        itemKillSuccessMapper.updateByExampleSelective(ItemKillSuccess.builder().status(i).build(),example);
    }

    @Override
    public List<ItemKillSuccess> selectStatusIsZeroAll() {
        Example example = new Example(ItemKillSuccess.class);
        example.createCriteria().andEqualTo("status",0);
        return itemKillSuccessMapper.selectByExample(example);
    }

}
