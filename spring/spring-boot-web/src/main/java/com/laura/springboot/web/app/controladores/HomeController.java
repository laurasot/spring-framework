package com.laura.springboot.web.app.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "redirect:/app/index";
    }
}
