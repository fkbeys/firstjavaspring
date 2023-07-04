package com.kayaspring.kayaspring.api.Middlewares.HealthCheck;

import com.kayaspring.kayaspring.Data.Repositories.IUserRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    private final IUserRepository userService;

    public HealthCheck(IUserRepository userService) {
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

