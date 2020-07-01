package com.gukki.service;

import com.gukki.entity.PermissionRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gukki.entity.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DvDxD
 * @since 2020-06-27
 */
public interface PermissionRoleService extends IService<PermissionRole> {
    public List<Permission> getPremissionByID(long id);
}
