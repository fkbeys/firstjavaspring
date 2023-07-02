package com.kayaspring.kayaspring.Entities.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "AppUser")

public class AppUser extends BaseClass {

    public String username;
    public String password;
    public String email;
    public int mainLanguage;
    public int targetLanguage;

}
