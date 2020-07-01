package com.gukki.mapper;

import com.gukki.entity.Role;
import com.gukki.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface UserRoleMapper extends BaseMapper<UserRole> {
    public List<Role> getRoleByID(long id);
}
