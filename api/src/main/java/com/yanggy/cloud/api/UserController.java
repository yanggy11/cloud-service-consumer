package com.yanggy.cloud.api;

import com.yanggy.cloud.param.UserParam;
import com.yanggy.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/29 14:47
 * @Description:
 */

@RestController
@RequestMapping("/users/**")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping(value = "list")
    public Object getUserList(@RequestBody UserParam userParam) {

        return userService.getUser(userParam);
    }
}
