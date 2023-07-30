package com.laurasoto.springrest.Qualifiers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Perro extends Animal{
    public Perro(@Value("Rocky")String nombre,@Value("10") Integer edad){
        super(edad, nombre);
    }

}
