package com.yanggy.cloud.service.impl;

import com.yanggy.cloud.mapper.UserMapper;
import com.yanggy.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/13 12:00
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Object getUser() {
        return restTemplate.postForEntity("http://CLOUD-SERVICE-PROVIDER/api/user/userList",Object.class, null);
    }
}
