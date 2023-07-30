package com.laurasoto.springrest.Autowire;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaCalculatorService {
    private List<Figure> figures;
    public double calcAreas(){
        double area=0;
        for (Figure figure : figures){
            area+= figure.calculatorArea();
        }
        return area;
    }
}
