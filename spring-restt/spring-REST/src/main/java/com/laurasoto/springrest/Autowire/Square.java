package com.laurasoto.springrest.Autowire;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Square implements Figure {
    @Value("2.5")
    private double side;
    @Override
    public double calculatorArea() {
        return side*side;
    }
}
