package com.rest.apidemorest.controllers;

import com.rest.apidemorest.entities.Profile;
import com.rest.apidemorest.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users/{userId}/profiles")//asegurarse que cada perfil tenga un usuario
@RestController
public class ProfileController {
    @Autowired
    private ProfileService service;
    @PostMapping
    public ResponseEntity<Profile> create(@PathVariable Integer userId, @RequestBody Profile profile){
        return new ResponseEntity<Profile>(service.createProfile(userId,profile), HttpStatus.CREATED);
    }
    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Integer userId, @PathVariable Integer profileId){
        return new ResponseEntity<Profile>(service.getByUserIdAndProfileId(userId, profileId), HttpStatus.OK);
    }
}
