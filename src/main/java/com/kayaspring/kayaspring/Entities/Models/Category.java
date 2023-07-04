package com.kayaspring.kayaspring.Entities.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "category")
public class Category extends BaseClass {

    public Category() {

    }

    public Category(@NotNull String name, @NotNull Integer headerId, @NotNull Integer subId, @NotNull long createUserId) {
        this.name = name;
        this.headerId = headerId;
        this.subId = subId;
        this.id = this.id == null ? 0 : this.id;
        this.createUser = createUserId;
        this.createDate = new Date();
    }

    @NotNull
    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    @NotNull
    private Integer headerId;

    @Column(nullable = false)
    @NotNull
    private Integer subId;

    private String imagePath;

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
