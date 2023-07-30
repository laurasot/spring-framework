package com.rest.apidemorest;

import com.github.javafaker.Faker;
import com.rest.apidemorest.entities.Role;
import com.rest.apidemorest.entities.User;
import com.rest.apidemorest.entities.UserInRole;
import com.rest.apidemorest.repositories.RoleRepository;
import com.rest.apidemorest.repositories.UserInRoleRepository;
import com.rest.apidemorest.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ApiDemoRestApplication implements ApplicationRunner {
    @Autowired
    private Faker faker;
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;
    private static final Logger log = LoggerFactory.getLogger(ApiDemoRestApplication.class);
    public static void main(String[] args) {
        // Cuando se inicia una aplicación de Spring Boot, el método main() es el punto de entrada y se
        // ejecuta primero. Es en este método donde se crea el contexto de la aplicación y se inicia
        // el ciclo de vida de la aplicación. Una vez que el contexto de la aplicación está cargado y
        // configurado, se invoca el método run()
        SpringApplication.run(ApiDemoRestApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Interfaz con el unico metodo run
        //manera conveniente de ejecutar tareas o configuraciones adicionales al iniciar la aplicación
        List<Role> roles = new ArrayList<> (){{
                   add(new Role("ADMIN"));
                   add(new Role("SUPPORT"));
                   add(new Role("USER"));}};
        roleRepository.saveAll(roles);

        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setUsername(faker.name().username());
            user.setPassword(faker.hacker().adjective().toString());
            User created = repository.save(user);

            UserInRole userInRole = new UserInRole(created, roles.get(new Random().nextInt(roles.size())));
            log.info("User created username {} password {}, role: {}",
                    created.getUsername(),created.getPassword(), userInRole.getRole().getName());
            userInRoleRepository.save(userInRole);
        }
    }
}
