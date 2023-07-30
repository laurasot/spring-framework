package com.laurasoto.springboot.di.app.servicios;

import org.springframework.stereotype.Service;

@Service
public class IndexServicio {
    public String operacion(){
        return "ejecutando proceso importante";
    }
}
