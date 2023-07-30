package com.laurasoto.springboot.di.app.controladores;

import com.laurasoto.springboot.di.app.servicios.IndexServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {
    @Autowired
    private IndexServicio indexServicio;
    @GetMapping({"/","","/index"})
    public String index(Model model){
        model.addAttribute("objeto", indexServicio.operacion());
        return "index";
    }
}
