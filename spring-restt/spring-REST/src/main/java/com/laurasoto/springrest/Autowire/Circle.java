package com.laurasoto.springrest.Autowire;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Circle implements Figure{
    @Value("3.5")
    private double radius;
    @Override
    public double calculatorArea() {
        return Math.PI*Math.pow(radius, 2);
    }
}
