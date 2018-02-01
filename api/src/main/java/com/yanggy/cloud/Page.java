package com.yanggy.cloud;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/17 16:21
 * @Description:
 */

@Data
public class Page<T> implements Serializable {
    private int page;
    private int pageSize;
    private int totalRecord;
    private int totalPage;

    T data;
}
