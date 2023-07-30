package com.laurasoto.springrest.Qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Avion implements Volador{
    private final Logger log = LoggerFactory.getLogger(Avion.class);
    @Override
    public void volar() {
        log.info("soy un avion y estoy volando");
    }
}
