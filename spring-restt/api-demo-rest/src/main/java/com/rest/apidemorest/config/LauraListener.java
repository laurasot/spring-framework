package com.rest.apidemorest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LauraListener {
    private static final Logger log = LoggerFactory.getLogger(LauraListener.class);
    @KafkaListener(topics = "laura-topic", groupId = "laura") //groupid buscar en la otra configuracion el map
    public void listen(String message){
        log.info("Code to post  the message in the audit api {}", message);
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
