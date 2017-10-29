package com.yanggy.cloud.service;

import com.yanggy.cloud.param.UserParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yangguiyun on 2017/10/13.
 */

@FeignClient("CLOUD-SERVICE-PROVIDER")
public interface UserService {
    @RequestMapping(value = "/api/user/userList", method = RequestMethod.POST)
    Object getUser(UserParam userParam);
}
