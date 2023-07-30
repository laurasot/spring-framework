package com.rest.apidemorest.repositories;

import com.rest.apidemorest.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address,Integer> {
    @Query("SELECT a FROM Address a WHERE a.profile.id = ?2 AND a.profile.user.id = ?1")
    List<Address> findByUserIdAndProfileId(Integer userId, Integer profileId);
}
