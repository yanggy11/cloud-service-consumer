package com.yanggy.cloud.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanggy.cloud.dto.Page;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by derrick.yang on 11/3/17.
 */

@Component
public class UserListener {
    private static ObjectMapper objectMapper;

    @RabbitListener(queues = "hello", containerFactory = "rabbitListenerContainerFactory")
    @RabbitHandler
    public void userList(Page message) {
        System.out.println(message);
    }

    private static class SingleObjectMapper {
        private static ObjectMapper instance = new ObjectMapper();
    }

    public static ObjectMapper getObjectMapper() {
        return SingleObjectMapper.instance;
    }
}
