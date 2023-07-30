package com.example.springbatch.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Persona {
    private String primerNombre;
    private String segundoNombre;
    private String telefono;

    @Override
    public String toString() {
        return "Persona{" +
                "primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
