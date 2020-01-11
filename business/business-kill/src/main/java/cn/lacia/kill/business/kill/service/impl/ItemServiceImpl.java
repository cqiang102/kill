package cn.lacia.kill.business.kill.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lacia.kill.business.kill.mapper.ItemMapper;
import cn.lacia.kill.business.kill.service.ItemService;
/**
@create 2020/1/11 - 19:07
@author    你是电脑
*/
@Service
public class ItemServiceImpl implements ItemService{

    @Resource
    private ItemMapper itemMapper;

}
