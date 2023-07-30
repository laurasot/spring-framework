package com.laura.springboot.web.app.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {
    private String nombre;
    private String apellido;

    private String email;
}
