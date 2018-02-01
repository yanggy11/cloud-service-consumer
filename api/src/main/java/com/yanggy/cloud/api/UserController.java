package com.yanggy.cloud.api;

import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.UserParam;
import com.yanggy.cloud.service.UserContract;
import com.yanggy.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/29 14:47
 * @Description:
 */

@RestController
@RequestMapping("/users/**")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserContract userContract;
    @PostMapping(value = "list")
    public Object getUserList(@RequestBody UserParam userParam) {

        return userContract.userList(userParam);// restTemplate.postForEntity("http://CLOUD-SERVICE-PROVIDER//api/user/userList", userParam, ResponseEntity.class).getBody();

    }

    @PostMapping(value = "getUserById")
    public ResponseEntity<?> getUserById(@RequestBody UserParam userParam) {
        return restTemplate.postForEntity("http://CLOUD-SERVICE-PROVIDER/api/user/getUserById",userParam, ResponseEntity.class).getBody();
    }
}
