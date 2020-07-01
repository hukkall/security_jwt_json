package com.gukki;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gukki.entity.Role;
import com.gukki.entity.User;
import com.gukki.mapper.UserMapper;
import com.gukki.mapper.UserRoleMapper;
import com.gukki.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class SecSqlApplicationTests {
    @Autowired
    UserRoleMapper mapper;

    @Test
    void contextLoads() {
        List<Role> role = mapper.getRoleByID(1L);
        for (Role role1 : role) {
            System.out.println(role1);
        }
    }
}
