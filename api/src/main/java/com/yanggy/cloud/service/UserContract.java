package com.yanggy.cloud.service;

import com.yanggy.cloud.Page;
import com.yanggy.cloud.User;
import com.yanggy.cloud.UserParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by derrick.yang on 2/1/18.
 */

@FeignClient(name = "CLOUD-SERVICE-PROVIDER")
public interface UserContract {
    @RequestMapping(value = "/api/user/userList", method = RequestMethod.POST)
    Page<List<User>> userList(UserParam userParam);
}
