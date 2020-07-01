package com.gukki.service.impl;

import com.gukki.entity.Role;
import com.gukki.entity.UserRole;
import com.gukki.mapper.UserRoleMapper;
import com.gukki.service.UserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    UserRoleMapper mapper;
    @Override
    public List<Role> getRoleByID(long id) {
        return mapper.getRoleByID(id);
    }
}
