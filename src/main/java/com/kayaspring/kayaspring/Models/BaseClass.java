package com.kayaspring.kayaspring.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;


@MappedSuperclass

public class BaseClass {
    @Id
    @GeneratedValue
    public Long id;
    public Date createDate;
    public int createUser;

}
