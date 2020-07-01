package com.gukki.service;

import com.gukki.entity.Role;
import com.gukki.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DvDxD
 * @since 2020-06-27
 */
public interface UserRoleService extends IService<UserRole> {
    public List<Role> getRoleByID(long id);
}
