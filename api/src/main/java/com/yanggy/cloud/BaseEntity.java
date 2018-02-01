package com.yanggy.cloud;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by derrick.yang on 10/20/17.
 */

@Data
public class BaseEntity implements Serializable{
    private Long id;
    private Date createTime;
    private Date updateTime;
    private byte deleteFlag;
}
