package com.kayaspring.kayaspring.Api.Controllers;

import com.kayaspring.kayaspring.Data.Repositories.IUserRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {

    private final IUserRepository userService;

    public MyHealthIndicator(IUserRepository userService) {
        this.userService = userService;
    }

    @Override
    public Health health() {


        var isHealthy = userService.count() > 0 ? true : false;


        if (isHealthy) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }
}

