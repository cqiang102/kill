package cn.lacia.kill.business.kill.mapper;

import cn.lacia.kill.business.kill.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

/**
@create 2020/1/11 - 19:07
@author    你是电脑
*/
@Mapper
public interface ItemMapper extends MyMapper<Item> {
}