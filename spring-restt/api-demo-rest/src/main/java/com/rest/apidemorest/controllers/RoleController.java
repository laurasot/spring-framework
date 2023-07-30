package com.rest.apidemorest.controllers;

import com.rest.apidemorest.ApiDemoRestApplication;
import com.rest.apidemorest.entities.Role;
import com.rest.apidemorest.entities.User;
import com.rest.apidemorest.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService service;
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @GetMapping
    public ResponseEntity<List<Role>> getRoles(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Name {}", authentication.getName());
        log.info("Principal {}", authentication.getPrincipal());
        log.info("Credenciales {}", authentication.getCredentials());
        log.info("Roles {}", authentication.getAuthorities().toString());
        return new ResponseEntity<>(service.getRoles(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("{rolename}/users")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String rolename){
        return new ResponseEntity<>(service.getUserByRole(rolename), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        return new ResponseEntity<>(service.saveRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer roleId, @RequestBody Role role){
        return new ResponseEntity<>(service.updateRole(role, roleId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer roleId){
        service.deleteRole(roleId);
        return new ResponseEntity<Void>( HttpStatus.OK);


        //hacer endpoint de asignacion de un rol a un usuario
        //buscar los usuario segun el rol
    }
}
