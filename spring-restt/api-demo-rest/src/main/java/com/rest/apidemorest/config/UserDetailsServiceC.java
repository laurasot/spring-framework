package com.rest.apidemorest.config;

import com.rest.apidemorest.entities.User;
import com.rest.apidemorest.entities.UserInRole;
import com.rest.apidemorest.repositories.UserInRoleRepository;
import com.rest.apidemorest.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceC implements UserDetailsService {
    //uthenticacion de usuario, el usuario da el nombre  spring busca en la base de datos si  existe un usuario,
    // y hacer la validacion correspondiente

    private final UserRepository userRepository;
    private final UserInRoleRepository userInRoleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserDetailsServiceC(UserRepository userRepository,UserInRoleRepository userInRoleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userInRoleRepository = userInRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isPresent()){
            User user = optional.get();
            List<UserInRole> userInRoles = userInRoleRepository.findByUser(user);
            String[] roles = userInRoles.stream()
                    .map(r -> r.getRole().getName())
                    .toArray(String[]::new);
            return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
        }else{
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

    }
}
