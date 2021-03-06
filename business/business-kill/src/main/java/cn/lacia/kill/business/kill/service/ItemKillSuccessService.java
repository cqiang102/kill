package cn.lacia.kill.business.kill.service;

import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.business.kill.domain.SuccessInfo;
import cn.lacia.kill.commons.domain.ItemKillSuccess;

import java.util.List;

/**
 * @author 你是电脑
 * @create 2020/1/11 - 19:09
 */
public interface ItemKillSuccessService {

    boolean insert(ItemKill kill, String userId);

    ItemKillSuccess selectItemSuccessByCodeAndStatusIsZero(String code);

    SuccessInfo selectItemSuccessByCode(String code);

    void updateStatusByCode(String code, byte i);

    List<ItemKillSuccess> selectStatusIsZeroAll();
}

