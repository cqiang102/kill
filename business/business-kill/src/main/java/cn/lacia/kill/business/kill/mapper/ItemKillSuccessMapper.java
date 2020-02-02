package cn.lacia.kill.business.kill.mapper;

import cn.lacia.kill.business.kill.domain.SuccessInfo;
import cn.lacia.kill.commons.domain.ItemKillSuccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Mapper
public interface ItemKillSuccessMapper extends MyMapper<ItemKillSuccess> {

    SuccessInfo selectSuccessInfo(String code);
    List<ItemKillSuccess> selectStatusIsZeroAll();
    List<ItemKillSuccess> selectByItemIdAndUserIdAndStatus(@Param("itemId") Integer itemId,@Param("userId")Integer userId);
    ItemKillSuccess selectOneByCode(@Param("code") String code);
}
