package com.yanggy.cloud;

import com.yanggy.cloud.entity.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangguiyun on 2017/6/1.
 */

@Data
public class User  extends BaseEntity {
    private static final long serialVersionUID = 8898935594776534163L;

    private String name;
    private String password;
    private int sex;
    private int age;
    private String email;
    private String phone;
    private List<String> authorities = new ArrayList<>();
}
