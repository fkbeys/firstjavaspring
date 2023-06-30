package com.kayaspring.kayaspring.Models;


import jakarta.persistence.*;

@Entity
@Table
public class Category extends BaseClass {
    @Id
    @SequenceGenerator(name = "sequence_category", sequenceName = "sequence_category", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_category")
    public Long id;

    public String name;

    //each category may have a sub or header category...
    public int headerId;
    public int subId;
}
