package com.kayaspring.kayaspring.Api.Controllers;

import com.kayaspring.kayaspring.Auth.AuthenticationService;
import com.kayaspring.kayaspring.Business.Managers.IUserManager;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Entities.ReqAndResponses.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Api/")
public class LoginController {

    private final AuthenticationService authenticationService;
    private final IUserManager userManager;

    @Autowired
    public LoginController(AuthenticationService authenticationService, IUserManager userManager) {
        this.authenticationService = authenticationService;
        this.userManager = userManager;
    }

    @PostMapping("/Login")
    public GenericResultClass login(@RequestBody AuthRequest authRequest) {

        if (authenticate(authRequest.username, authRequest.password)) {
            String token = authenticationService.generateToken(authRequest.username);
            return GenericResultClass.Success(token, 0);
        }
        return GenericResultClass.UnSuccessful("Wrong username or password");
    }

    private boolean authenticate(String username, String password) {
        var isLoginSuccess = userManager.existsByUsernameAndPassword(username, password);
        return isLoginSuccess;
    }
}
