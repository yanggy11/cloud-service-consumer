package com.yanggy.cloud.service;

import com.yanggy.cloud.entity.User;

import java.util.Map;

/**
 * Created by yangguiyun on 2017/6/15.
 */

public interface IUserService {
    User getUserById(long id);
    Map login(User user);
    Map register( User user);
}
