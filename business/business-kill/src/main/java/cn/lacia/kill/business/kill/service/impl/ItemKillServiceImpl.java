package cn.lacia.kill.business.kill.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lacia.kill.business.kill.mapper.ItemKillMapper;
import cn.lacia.kill.business.kill.service.ItemKillService;
/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Service
public class ItemKillServiceImpl implements ItemKillService{

    @Resource
    private ItemKillMapper itemKillMapper;

}
