package com.gukki.service.impl;

import com.gukki.entity.User;
import com.gukki.mapper.UserMapper;
import com.gukki.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DvDxD
 * @since 2020-06-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
