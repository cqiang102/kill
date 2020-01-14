package cn.lacia.kill.business.kill.service;

import cn.lacia.kill.business.kill.domain.Item;
import cn.lacia.kill.business.kill.domain.ItemKill;

import java.util.List;

/**
@create 2020/1/11 - 19:07
@author    你是电脑
*/
public interface ItemService{

    /**
     * 获取待秒杀商品列表
     * @return
     * @throws Exception
     */
    List<ItemKill> getKillItems() throws Exception;

    /**
     * 根据 ID 查询商品详情
     * @param id
     * @return
     * @throws Exception
     */
    Item getItemDetailById(String id) throws Exception;
}
