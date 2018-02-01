package com.yanggy.cloud;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/17 12:57
 * @Description:
 */

@Data
public class UserParam implements Serializable {
    private Long userId;
    private String name;
    private String password;
    private String confirmPassword;
    private int page;
    private int pageSize = 15;
}
