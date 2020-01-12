package cn.lacia.kill.business.kill.service;

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
}
