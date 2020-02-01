package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.config.KillException;
import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.business.kill.mapper.ItemKillSuccessMapper;
import cn.lacia.kill.business.kill.service.ItemKillSuccessService;
import cn.lacia.kill.commons.domain.ItemKillSuccess;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lacia.kill.business.kill.mapper.ItemKillMapper;
import cn.lacia.kill.business.kill.service.ItemKillService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Service
public class ItemKillServiceImpl implements ItemKillService{

    @Resource
    private ItemKillMapper itemKillMapper;
    @Resource
    private ItemKillSuccessMapper itemKillSuccessMapper;
    @Resource
    private ItemKillSuccessService itemKillSuccessService;

    @Override
    @Transactional(readOnly = false,rollbackFor= KillException.class)
    public boolean killItem(Integer killId, Integer userId) throws KillException {
        // 判断当前用户是否已经抢购过改商品
        List<ItemKillSuccess> select = itemKillSuccessMapper.select(ItemKillSuccess.builder()
                .itemId(killId)
                .userId(userId.toString()).build());
        if (Objects.isNull(select) || select.isEmpty()) {
            // 查询待秒杀商品详情
            ItemKill itemKill = itemKillMapper.selectItemKillById(killId.toString());
            // 判断是否可秒杀
            if (itemKill != null && 1 == itemKill.getCanKill() && itemKill.getTotal() > 0) {
//                Example example = new Example(ItemKill.class);
//                itemKill.setItemName(null);
//                itemKill.setCanKill(null);
//                // 库存减一
//                itemKill.setTotal(itemKill.getTotal() - 1);
//                // 更新时再次判断库存大于0
//                example.createCriteria().andGreaterThan("total", 0).andEqualTo("id", itemKill.getId());
//                int i = itemKillMapper.updateByExampleSelective(itemKill, example);
                int i = itemKillMapper.updateTotalByKillId(itemKill,userId.toString());
                // 减库存是否成功
                if (i > 0) {
                    // TODO 生成订单 ， 通知用户
                    return itemKillSuccessService.insert(itemKill, userId.toString());

                }
            }

        }
        throw new KillException("123");
    }

}
