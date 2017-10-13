package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.mapper.UserMapper;
import com.yanggy.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/13 12:00
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Object getUser() {
        return userMapper.getUserList();
    }
}
