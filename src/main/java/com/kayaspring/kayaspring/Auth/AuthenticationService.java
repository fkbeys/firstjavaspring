package com.kayaspring.kayaspring.Auth;

import com.kayaspring.kayaspring.Entities.Models.User.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public UserDetailsImpl getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            throw new IllegalStateException("Unknown user principal: " + authentication.getPrincipal());
        }
        return (UserDetailsImpl) authentication.getPrincipal();
    }
}
