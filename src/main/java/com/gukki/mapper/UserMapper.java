package com.gukki.mapper;

import com.gukki.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DvDxD
 * @since 2020-06-27
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
