package com.kayaspring.kayaspring.Entities.Requests;

import java.util.List;
import java.util.Set;

public class SignupRequest {

    public String username;
    public String email;
    public String password;
//    public Set<String> role;
     public String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
