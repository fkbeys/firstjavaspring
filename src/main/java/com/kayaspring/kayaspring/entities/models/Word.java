package com.kayaspring.kayaspring.entities.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Word")
public class Word extends BaseClass {

    public int languageId;
    public String catogoryIds; // a word may be in multiple categories
    public String name;
}
