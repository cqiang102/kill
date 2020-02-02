package cn.lacia.kill.business.kill.service;

import cn.lacia.kill.business.kill.config.KillException;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
public interface ItemKillService{

    boolean killItem(Integer itemId,Integer userId) throws KillException;
}
