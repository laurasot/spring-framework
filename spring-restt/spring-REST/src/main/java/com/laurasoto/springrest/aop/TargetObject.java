package com.laurasoto.springrest.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TargetObject {
    private static final Logger log = LoggerFactory.getLogger(TargetObject.class);
    public void hello(String message){
        log.info(message);
    }
}
