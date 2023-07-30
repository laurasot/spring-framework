package com.rest.apidemorest.controllers;

import com.rest.apidemorest.entities.Address;
import com.rest.apidemorest.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddreses(@PathVariable Integer userId,@PathVariable Integer profileId){
        return new ResponseEntity<>(addressService.getAddresses(userId, profileId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Address> createAddress(@PathVariable Integer userId, @PathVariable Integer profileId,
                                                 @RequestBody Address address){
        return new ResponseEntity<>(addressService.saveAddress(userId, profileId, address), HttpStatus.CREATED);
    }
}
