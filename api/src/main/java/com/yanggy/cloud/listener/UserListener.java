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
@RabbitListener(queues = "hello")
public class UserListener {
    private static ObjectMapper objectMapper;

    @RabbitHandler
    public void userList(Object message) {
        Message msg = (Message)message;
        String json = new String(msg.getBody());
        ObjectMapper objectMapper = this.getObjectMapper();
        try {
            Page page = objectMapper.readValue(json, Page.class);

            System.out.println(page.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SingleObjectMapper {
        private static ObjectMapper instance = new ObjectMapper();
    }

    public static ObjectMapper getObjectMapper() {
        return SingleObjectMapper.instance;
    }
}
