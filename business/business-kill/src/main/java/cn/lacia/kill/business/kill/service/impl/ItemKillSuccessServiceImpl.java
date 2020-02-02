package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.business.kill.domain.SuccessInfo;
import cn.lacia.kill.business.kill.mapper.ItemKillSuccessMapper;
import cn.lacia.kill.business.kill.service.ItemKillSuccessService;
import cn.lacia.kill.commons.domain.ItemKillSuccess;
import cn.lacia.kill.commons.utils.SnowFlake;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

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
        int insert = itemKillSuccessMapper.insertSelective(build);
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
    public ItemKillSuccess selectItemSuccessByCodeAndStatusIsZero(String code) {
        return itemKillSuccessMapper.selectOneByCode(code);
    }

    @Override
    public SuccessInfo selectItemSuccessByCode(String code) {
        return itemKillSuccessMapper.selectSuccessInfo(code);
    }


    @Override
    public void updateStatusByCode(String code, byte i) {
        ItemKillSuccess itemKillSuccess = itemKillSuccessMapper.selectOneByCode(code);
        if (itemKillSuccess == null) {
            return;
        }
        itemKillSuccess.setStatus(i);
        itemKillSuccessMapper.updateByPrimaryKeySelective(itemKillSuccess);
    }

    @Override
    public List<ItemKillSuccess> selectStatusIsZeroAll() {
        return itemKillSuccessMapper.selectStatusIsZeroAll();
    }

}
