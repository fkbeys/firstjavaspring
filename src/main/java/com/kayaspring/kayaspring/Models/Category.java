package com.kayaspring.kayaspring.Models;


import jakarta.persistence.*;

@Entity
@Table
public class Category extends BaseClass {

    public String name;

    //each category may have a sub or header category...
    public int headerId;
    public int subId;
}
