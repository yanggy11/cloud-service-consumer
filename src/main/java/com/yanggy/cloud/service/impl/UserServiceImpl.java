package com.yanggy.cloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.service.IUserService;
import com.yanggy.cloud.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangguiyun on 2017/6/15.
 */

@Service("userService")
public class UserServiceImpl implements IUserService{

    @Resource
    private RestTemplate restTemplate;

//    @HystrixCommand(fallbackMethod = "addGetUserByIdFallback")
    @Override
    public User getUserById(long id) {
        return restTemplate.getForEntity(Constants.CLOUD_SERVICE_PROVIDER + "/api/user/getUserById?id=" + id, User.class).
                getBody();
    }

//    @HystrixCommand(fallbackMethod = "mapFallBack")
    @Override
    public Map login(User user) {
        return restTemplate.postForEntity(Constants.CLOUD_SERVICE_PROVIDER + "/api/user/login",user,Map.class).
                getBody();
    }

//    @HystrixCommand(fallbackMethod = "mapFallBack")
    @Override
    public Map register(User user) {
        Map map = null;
        try {
            map = restTemplate.postForEntity(Constants.CLOUD_SERVICE_PROVIDER + "/api/user/register", user, Map.class).
                    getBody();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public List<User> getUserList() {
        return restTemplate.postForEntity(Constants.CLOUD_SERVICE_PROVIDER + "/api/user/userList",null,List.class).getBody();
    }

    public User addGetUserByIdFallback(long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public Map mapFallBack(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fallback","error");

        return map;
    }
}
