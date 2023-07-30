package com.rest.apidemorest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.apidemorest.entities.AuditDetails;
import com.rest.apidemorest.entities.Role;
import com.rest.apidemorest.entities.User;
import com.rest.apidemorest.entities.UserInRole;
import com.rest.apidemorest.repositories.RoleRepository;
import com.rest.apidemorest.repositories.UserInRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;

    @Autowired
    private KafkaTemplate<Integer,String> kafkaTemplate;

    private ObjectMapper mapper = new ObjectMapper();
    public List<User> getUserByRole(String roleName){
        return userInRoleRepository.findUserInRoleName(roleName);
    }
    public List<Role> getRoles(){
        return repository.findAll();
    }

    public Role saveRole(Role role){
        Role roleCreated = repository.save(role);

        AuditDetails details = new AuditDetails("hola",role.getName());
        try{
            kafkaTemplate.send("laura-topic", mapper.writeValueAsString(details));
        } catch (JsonProcessingException e){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error parsing the message");
        }

        return roleCreated;
    }

    public Role updateRole(Role role, Integer roleId){
        Optional<Role> result = repository.findById(roleId);
        if(result.isPresent()){
            return repository.save(role);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Role id %s doesn 't", roleId));
        }
    }

    public void deleteRole(Integer roleId){
        Optional<Role> result = repository.findById(roleId);
        if(result.isPresent()){
            repository.delete(result.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Role id %s doesn 't", roleId));
        }
    }


}
