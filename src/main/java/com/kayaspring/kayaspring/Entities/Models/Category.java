package com.kayaspring.kayaspring.Entities.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public class Category extends BaseClass {


    @NotNull
    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    @NotNull
    public int headerId;

    @Column(nullable = false)
    @NotNull
    public int subId;
}
