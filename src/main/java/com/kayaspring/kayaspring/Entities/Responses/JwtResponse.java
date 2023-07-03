package com.kayaspring.kayaspring.Entities.Responses;

import java.util.List;

public class JwtResponse {

    public JwtResponse(String token, long id, String type, String username, List<String> roles) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String token;
    public String type;
    public long id;
    public String username;
    public String email;
    public List<String> roles;
}
