package com.gukki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gukki.entity.PermissionRole;
import com.gukki.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author DvDxD
 * @since 2020-06-27
 */
@Repository
public interface PermissionRoleMapper extends BaseMapper<PermissionRole> {
    public List<Permission> getPremissionByID(long id);
}
