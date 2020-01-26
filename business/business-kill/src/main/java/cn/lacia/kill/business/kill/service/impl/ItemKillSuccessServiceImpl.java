package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.commons.domain.ItemKillSuccess;
import cn.lacia.kill.commons.utils.SnowFlake;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lacia.kill.business.kill.mapper.ItemKillSuccessMapper;
import cn.lacia.kill.business.kill.service.ItemKillSuccessService;

import java.util.Date;
import java.util.UUID;

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

    @Autowired
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
            return true;
        }
        return false;


    }

}
