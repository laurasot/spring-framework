package com.laura.springboot.web.app.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/variables")
@Controller
public class EjemploPathVariableController {
    @GetMapping("/string/{texto}")
    public String variables (@PathVariable String texto, Model model){
        model.addAttribute("pathbariable", texto);
        return "variables/ver";
    }
}
