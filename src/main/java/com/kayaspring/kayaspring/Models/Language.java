package com.kayaspring.kayaspring.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Language")
public class Language extends BaseClass {

    public String name;


}
