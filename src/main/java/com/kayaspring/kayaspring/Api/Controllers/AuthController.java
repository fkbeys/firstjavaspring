package com.kayaspring.kayaspring.Api.Controllers;

import com.kayaspring.kayaspring.Business.Services.AuthenticationService;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Entities.Requests.LoginRequest;
import com.kayaspring.kayaspring.Entities.Requests.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/signin")
    public GenericResultClass authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return authenticationService.authenticateUser(loginRequest);

    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authenticationService.registerUser(signUpRequest);
    }
}