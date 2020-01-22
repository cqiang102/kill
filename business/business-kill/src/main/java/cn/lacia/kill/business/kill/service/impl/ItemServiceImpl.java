package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.domain.Item;
import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.business.kill.mapper.ItemKillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lacia.kill.business.kill.mapper.ItemMapper;
import cn.lacia.kill.business.kill.service.ItemService;

import java.util.List;

/**
@create 2020/1/11 - 19:07
@author    你是电脑
*/
@Service
public class ItemServiceImpl implements ItemService{
    public static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Resource
    private ItemKillMapper itemKillMapper;
    @Resource
    private ItemMapper itemMapper;

    @Override
    public List<ItemKill> getKillItems() throws Exception {
        return itemKillMapper.selectAllItemKill();
    }

    @Override
    public ItemKill getItemDetailById(String id) throws Exception {
        return itemKillMapper.selectItemKillById(id);
    }
}
