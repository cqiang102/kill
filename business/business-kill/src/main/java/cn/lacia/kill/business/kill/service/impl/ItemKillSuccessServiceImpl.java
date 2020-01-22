package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.business.kill.domain.ItemKillSuccess;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lacia.kill.business.kill.mapper.ItemKillSuccessMapper;
import cn.lacia.kill.business.kill.service.ItemKillSuccessService;

import java.util.Date;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Service
public class ItemKillSuccessServiceImpl implements ItemKillSuccessService{

    @Resource
    private ItemKillSuccessMapper itemKillSuccessMapper;

    @Override
    public void insert(ItemKill kill, String userId){
        ItemKillSuccess build = ItemKillSuccess.builder()
                .killId(kill.getId())
                .userId(userId)
                .code("")
                .createTime(new Date())
                .status((byte) 0)
                .itemId(kill.getItemId())
                .build();
        int insert = itemKillSuccessMapper.insert(build);
        if (insert > 0){
        // TODO 发送邮件通知用户
        }
    }

}
