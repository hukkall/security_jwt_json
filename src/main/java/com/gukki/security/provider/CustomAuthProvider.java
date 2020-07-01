package com.gukki.security.provider;

import com.gukki.entity.Role;
import com.gukki.security.entity.SecurityUser;
import com.gukki.security.service.CustomUserDetailService;
import com.gukki.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;


@Slf4j
@Component
public class CustomAuthProvider implements AuthenticationProvider {
    @Autowired
    CustomUserDetailService userDetailService;
    @Autowired
    UserRoleService userRoleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("进入了登录监测");
        String name = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        SecurityUser user = userDetailService.loadUserByUsername(name);
        log.info(user.toString());
        if (user == null) {
            throw new UsernameNotFoundException("未找到用户名");
        } else if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
        // 获取角色列表
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        List<Role> list = userRoleService.getRoleByID(user.getId());
        //Lambda 表达式 将获取到的角色处理之后放入用户信息中的权限组
        list.forEach(userRole -> authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRoleName())));
        user.setAuthorities(authorities);
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
