package com.laurasoto.springrest.Qualifiers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Animal {
    private Integer edad;
    private String nombre;
}
