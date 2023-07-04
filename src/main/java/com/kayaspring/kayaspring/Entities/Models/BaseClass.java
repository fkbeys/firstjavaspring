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
    private Long id;


    public Long getId() {

        return id == null ? 0L : id;

    }

    public void setId(Long id) {

        if (this.id == null) {
            this.id = 0L;

        } else {
            this.id = id;

        }

    }

    @Column(name = "createDate", nullable = false)
    @NotNull
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    @Column(name = "createUser", nullable = false)
    @NotNull
    private long createUser;

}
