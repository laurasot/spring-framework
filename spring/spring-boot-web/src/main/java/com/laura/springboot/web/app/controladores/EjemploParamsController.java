package com.laura.springboot.web.app.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/params")
public class EjemploParamsController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/string")
    public String param(@RequestParam(required=false) String texto, Model model ){
        model.addAttribute("resultado", "el parametro enviado es:" + texto);
        return"params/ver";
    }
    @GetMapping("/mix-params")
    public String params(HttpServletRequest request, Model model){
        String saludo = request.getParameter("saludo");
        Integer numero = null;
        try {
            numero = Integer.parseInt(request.getParameter("numero"));
        } catch (NumberFormatException e){
            numero = 0;
        }
        model.addAttribute("resultado2", "hola" + saludo + "numero es" + numero );
        return"params/ver";
    }
}
