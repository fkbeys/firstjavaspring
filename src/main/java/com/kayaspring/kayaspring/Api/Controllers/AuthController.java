package com.kayaspring.kayaspring.Api.Controllers;

import com.kayaspring.kayaspring.Api.Middlewares.Logging.ILogger;
import com.kayaspring.kayaspring.Auth.JwtUtils;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.Repositories.IRoleRepository;
import com.kayaspring.kayaspring.Data.Repositories.IUserRepository;
import com.kayaspring.kayaspring.Entities.Enums.UserRoleEnums;
import com.kayaspring.kayaspring.Entities.Models.User.AppUser;
import com.kayaspring.kayaspring.Entities.Models.User.UserDetailsImpl;
import com.kayaspring.kayaspring.Entities.Models.User.UserRole;
import com.kayaspring.kayaspring.Entities.Requests.LoginRequest;
import com.kayaspring.kayaspring.Entities.Requests.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    private final ILogger logger;

    @Autowired
    JwtUtils jwtUtils;

    public AuthController(ILogger logger) {
        this.logger = logger;
    }

    @PostMapping("/signin")
    public GenericResultClass authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return GenericResultClass.Success(jwt, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        GenericResultClass result = null;
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            result = GenericResultClass.UnSuccessful("Error: Username is already taken!");
            return ResponseEntity.badRequest().body(result);
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            result = GenericResultClass.UnSuccessful("Error: Email is already in use!");
            return ResponseEntity.badRequest().body(result);
        }

        AppUser user = new AppUser(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        List<String> strRoles = Arrays.asList(signUpRequest.role.split(","));

        Set<UserRole> roles = new HashSet<>();

        if (strRoles == null) {
            UserRole userRole = roleRepository.findByName(UserRoleEnums.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        UserRole adminRole = roleRepository.findByName(UserRoleEnums.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        UserRole modRole = roleRepository.findByName(UserRoleEnums.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        UserRole userRole = roleRepository.findByName(UserRoleEnums.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        result = GenericResultClass.Success("User registered successfully!", 1);
        return ResponseEntity.ok(result);
    }
}