package com.yanggy.cloud.api;

import com.yanggy.cloud.utils.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by derrick.yang on 6/22/17.
 */

@RestController
@RequestMapping("/test/*")
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/test",  method = RequestMethod.GET)
    public String test() {
        return restTemplate.getForEntity(Constants.CLOUD_SERVICE_PROVIDER + "/test",String.class).getBody();
    }
}
