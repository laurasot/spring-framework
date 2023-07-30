package com.laurasoto.springrest.Profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("qa")
public class QaEnvironment implements EnvironmentService{
    @Override
    public String getEnvironment() {
        return "Qa";
    }
}
