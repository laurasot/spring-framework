package com.rest.apidemorest.services;

import com.rest.apidemorest.entities.User;
import com.rest.apidemorest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Page<User> getUsers( int page, int size){
        return repository.findAll(PageRequest.of(page, size));
    }

    public Page<String> getUsernames(int page, int size){
        return repository.findUsernames(PageRequest.of(page, size));
    }
    public User getUserById(Integer userId){
        return repository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found",userId)));
    }
    @CacheEvict("users")
    public void deleteUserByUsername(String username){
        User user = getUserByUsername(username);
        repository.delete(user);
    }
    @Cacheable("users")
    public User getUserByUsername(String username){
        return repository.findByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found",username)));
    }

    public User getUserByUsernameAndPassword(String username, String password){
        return repository.findByUsernameAndPassword(username, password).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found",username)));
    }

}
