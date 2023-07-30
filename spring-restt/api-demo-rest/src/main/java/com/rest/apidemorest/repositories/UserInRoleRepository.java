package com.rest.apidemorest.repositories;

import com.rest.apidemorest.entities.User;
import com.rest.apidemorest.entities.UserInRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer> {
    @Query("SELECT u.user FROM UserInRole u where u.role.name = ?1")
    List<User> findUserInRoleName(String roleName);

    List<UserInRole> findByUser(User user);
}
