package com.kayaspring.kayaspring.entities.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Language")
public class Language extends BaseClass {

    public String name;


}
