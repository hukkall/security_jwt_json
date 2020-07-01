package com.gukki.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gukki.entity.User;
import com.gukki.mapper.UserMapper;
import com.gukki.security.entity.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserMapper mapper;

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", username);
        wrapper.allEq(map);
        user = mapper.selectOne(wrapper);
        if (user != null) {
            SecurityUser securityUser = new SecurityUser();
            BeanUtils.copyProperties(user, securityUser);
            return securityUser;
        }
        return null;
    }
}
