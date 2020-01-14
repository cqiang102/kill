package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author 你是电脑
 * @create 2020/1/11 - 19:08
 */
public interface MyMapper<T> extends MySqlMapper<T> , Mapper<T> {
}
