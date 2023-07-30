package com.laurasoto.springrest.Qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Pajaro extends Animal implements Volador{
    public static final Logger log = LoggerFactory.getLogger(Pajaro.class);

    public Pajaro(@Value("3")Integer edad, @Value("Pajaro Loco") String nombre) {
        super(edad, nombre);
    }

    @Override
    public void volar() {
        log.info("soy una pajaro y estoy volando");
    }
}
