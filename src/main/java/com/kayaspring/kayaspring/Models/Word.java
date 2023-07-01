package com.kayaspring.kayaspring.Models;

import jakarta.persistence.*;


@Entity
@Table(name="Word")
public class Word extends BaseClass {

    public int languageId;
    public String catogoryIds; // a word may be in multiple categories
    public String name;
}
