package cn.lacia.kill.business.kill.service;

import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.commons.domain.ItemKillSuccess;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
public interface ItemKillSuccessService{

        boolean insert(ItemKill kill, String userId);

        ItemKillSuccess selectItemSuccessByCode(String code);

    void updateStatusByCode(String code, byte i);
}
