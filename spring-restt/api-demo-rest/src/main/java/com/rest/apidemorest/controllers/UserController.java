package com.rest.apidemorest.controllers;

import com.rest.apidemorest.entities.User;
import com.rest.apidemorest.services.UserService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping
    @Timed(value = "get.users")
    public ResponseEntity<Page<User>> getUsers(@RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                               @RequestParam(required = false, defaultValue = "100", value = "size") int size){
        return new ResponseEntity<>(service.getUsers(page, size), HttpStatus.OK);
    }
    @Timed(value = "usernames")
    @GetMapping("/usernames")
    public ResponseEntity<Page<String>> getUsernames(@RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                                     @RequestParam(required = false, defaultValue = "100", value = "size") int size){
        return new ResponseEntity<>(service.getUsernames(page, size), HttpStatus.OK);
    }
    @Timed(value = "user.id")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId){
        return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> authenticated(@RequestBody User user){
        return new ResponseEntity<>(service.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
    }
    @DeleteMapping("/{username}") //borra el usuario, pero aun puede seguir almacenandose desde el cache
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        service.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
