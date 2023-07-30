package com.laurasoto.springrest.Profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdEnvironment implements EnvironmentService{
    @Override
    public String getEnvironment() {
        return "Prod";
    }
}
