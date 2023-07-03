package com.kayaspring.kayaspring.Entities.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseClass {

    public String name;
    public int headerId;

    public int subId;
}
