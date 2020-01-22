package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.business.kill.domain.ItemKillSuccess;
import cn.lacia.kill.business.kill.mapper.ItemKillSuccessMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lacia.kill.business.kill.mapper.ItemKillMapper;
import cn.lacia.kill.business.kill.service.ItemKillService;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public boolean killItem(Integer killId, Integer userId) {
        // 判断当前用户是否已经抢购过改商品
        if (itemKillSuccessMapper.select(ItemKillSuccess.builder()
                .killId(killId)
                .userId(userId.toString()).build())
                .isEmpty()) {
            // 查询待秒杀商品详情
            ItemKill itemKill = itemKillMapper.selectItemKillById(killId.toString());
            // 判断是否可秒杀
            if (itemKill != null&& 1 == itemKill.getCanKill()&& itemKill.getTotal() >0) {
                // 库存减一
                Example example = new Example(ItemKill.class);
                itemKill.setTotal(itemKill.getTotal() -1);
                example.createCriteria().andEqualTo("canKill",1).andGreaterThan("total",0);
                int i = itemKillMapper.updateByExampleSelective(itemKill, example);
                // 减库存是否成功
                if (i>0){
                    // TODO 生成订单 ， 通知用户

                    return true;
                }
            }
        }
        return false;
    }
}
