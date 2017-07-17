package com.yanggy.cloud.api;

import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by yangguiyun on 2017/6/12.
 */

@RestController
@RequestMapping(value="/api/user")
public class ConsumerController {
    private static Logger logger = LogManager.getLogger(ConsumerController.class);
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private IUserService userService;

    @RequestMapping(value="/selectUserById", method = RequestMethod.GET)
    public User getUserById(long id) {
        logger.info("api----selectUserById:" + id);
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody User user) {
        logger.info("api----login:" + new JSONObject(user).toString());
        return userService.login(user);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Map register(@RequestBody User user) {
        logger.info("api----register:" + new JSONObject(user).toString());
        return userService.register(user);
    }

    @RequestMapping(value="/userList", method = RequestMethod.POST)
    public Object getUserList() {
        return userService.getUserList();
    }
}
