package com.kayaspring.kayaspring.Entities.Models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;


@MappedSuperclass

public class BaseClass {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "createDate")
    public Date createDate;

    @Column(name = "createUser")
    public int createUser;

}
