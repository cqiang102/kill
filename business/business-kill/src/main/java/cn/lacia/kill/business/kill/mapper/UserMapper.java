package cn.lacia.kill.business.kill.mapper;

import cn.lacia.kill.business.kill.domain.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.MyMapper;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Mapper
public interface UserMapper extends MyMapper<User> {
}