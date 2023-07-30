package com.laurasoto.springrest.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HolaControlador {
    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<>("Holaaa pelotudos!", HttpStatus.OK);
    }
}
