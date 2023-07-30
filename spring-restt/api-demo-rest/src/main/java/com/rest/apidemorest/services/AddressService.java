package com.rest.apidemorest.services;

import com.rest.apidemorest.entities.Address;
import com.rest.apidemorest.entities.Profile;
import com.rest.apidemorest.repositories.AddressRepository;
import com.rest.apidemorest.repositories.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ProfileRepository profileRepository;
    public AddressService(AddressRepository addressRepository, ProfileRepository profileRepository){
        this.addressRepository = addressRepository;
        this.profileRepository = profileRepository;
    }

    public List<Address> getAddresses(Integer userId, Integer profileId){
        return addressRepository.findByUserIdAndProfileId(userId, profileId);
    }

    public Address saveAddress(Integer userId, Integer profileId, Address address){
        Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId,profileId);
        if(result.isPresent()){
            address.setProfile(result.get());
            return addressRepository.save(address);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile %d and user %d not found", profileId, userId));
        }
    }

}
