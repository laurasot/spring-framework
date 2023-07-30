package com.rest.apidemorest.repositories;

import com.rest.apidemorest.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //Role findById(Integer id);
}
