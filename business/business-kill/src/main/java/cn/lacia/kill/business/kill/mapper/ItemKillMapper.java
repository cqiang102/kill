package cn.lacia.kill.business.kill.mapper;

import cn.lacia.kill.business.kill.domain.ItemKill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Mapper
public interface ItemKillMapper extends MyMapper<ItemKill> {

    List<ItemKill> selectAllItemKill();
    ItemKill selectItemKillById(String id);
    int updateTotalByKillId( @Param("id")String id, @Param("userId") String userId);
}
