package com.laura.springboot.web.app.controladores;

import com.laura.springboot.web.app.modelos.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class IndexControlador {
    @Value("${texto.index}")
    private String textoIndex;
    @GetMapping({"/index","/home", "/"})
    public String index(ModelMap model, Map<String, Object> map){
        //se puede enviar info a las vistas, ya sea con model, modelMap o map o ModelAndView
        model.addAttribute("titulo", "holap");
        map.put("titulo2", "holi");
        return "index";
    }

    @GetMapping("/perfil")
    public String perfil(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("LAura");
        usuario.setApellido("soto");
        usuario.setEmail("lora@gmail.com");
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Perfil del usuario ".concat(usuario.getNombre()));
        return"perfil";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        List<Usuario> usuarios = new ArrayList<>();
        //segunda forma de hacer lista utilizando metodo estatico de la clase array
        List<Usuario> usuarios2 = Arrays.asList(new Usuario("Lora", "soto", "lora@gmail.com"),
                new Usuario("Lorita", "soto", "laura@gmail.com"));

        usuarios.add(new Usuario("Lora", "soto", "lora@gmail.com"));
        usuarios.add(new Usuario("Lorita", "soto", "laura@gmail.com"));


        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("usuarios",usuarios);
        return "listar";
    }

    @ModelAttribute("usuarios3")
    public List<Usuario> poblarUsuarios(){
        List<Usuario> usuarios3 = Arrays.asList(new Usuario("Lora", "soto", "lora@gmail.com"),
                new Usuario("Lorita", "soto", "laura@gmail.com"));
        return usuarios3;    }
}
