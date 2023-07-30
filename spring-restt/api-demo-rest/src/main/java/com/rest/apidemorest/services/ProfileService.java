package com.rest.apidemorest.services;

import com.rest.apidemorest.entities.Profile;
import com.rest.apidemorest.entities.User;
import com.rest.apidemorest.repositories.ProfileRepository;
import com.rest.apidemorest.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository,UserRepository userRepository){
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }
    public Profile createProfile(Integer userId, Profile profile){
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()){
            profile.setUser(result.get());
            return profileRepository.save(profile);
        }else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %d not found", userId));
        }
    }

    public Profile getByUserIdAndProfileId(Integer userId, Integer profileId){
        return profileRepository.findByUserIdAndProfileId(userId, profileId)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Profile not found for user %d and profile %d", userId, profileId)));
    }
}
