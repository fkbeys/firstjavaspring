package com.kayaspring.kayaspring.Entities.Models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


@MappedSuperclass

public class BaseClass {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @NotNull
    public Long id;

    @Column(name = "createDate", nullable = false)
    @NotNull
    public Date createDate;

    @Column(name = "createUser", nullable = false)
    @NotNull
    public long createUser;


}
