package com.gukki.service.impl;

import com.gukki.entity.PermissionRole;
import com.gukki.entity.Permission;
import com.gukki.mapper.PermissionRoleMapper;
import com.gukki.service.PermissionRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DvDxD
 * @since 2020-06-27
 */
@Service
public class PermissionRoleServiceImpl extends ServiceImpl<PermissionRoleMapper, PermissionRole> implements PermissionRoleService {
    @Autowired
    PermissionRoleMapper mapper;
    @Override
    public List<Permission> getPremissionByID(long id) {
        return mapper.getPremissionByID(id);
    }
}
