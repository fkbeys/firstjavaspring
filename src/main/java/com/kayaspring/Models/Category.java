package com.kayaspring.Models;

public class Category extends BaseClass {
    public int id;
    public String name;

    //each category may have a sub or header category...
    public int headerId;
    public int subId;
}
