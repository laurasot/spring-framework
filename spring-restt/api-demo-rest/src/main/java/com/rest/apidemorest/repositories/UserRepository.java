package com.rest.apidemorest.repositories;

import com.rest.apidemorest.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);


    Optional<User> findByUsernameAndPassword(String username, String password);
    // lenguaje JPQL
    @Query("SELECT u.username FROM User u")
    Page<String> findUsernames(Pageable pageable);

}
