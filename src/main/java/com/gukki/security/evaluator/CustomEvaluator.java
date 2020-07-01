package com.gukki.security.evaluator;

import com.gukki.entity.Permission;
import com.gukki.security.entity.SecurityUser;
import com.gukki.service.PermissionRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class CustomEvaluator implements PermissionEvaluator {
    @Autowired
    PermissionRoleService service;
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        Set<String> permissions = new HashSet<>();
        List<Permission> permissionList = service.getPremissionByID(user.getId());
        permissionList.forEach(item -> {permissions.add(item.getPermission());});
        if(permissions.contains(permission.toString())) return true;
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
