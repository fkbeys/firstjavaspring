package com.kayaspring.kayaspring.Data.DynamicSortAndFilters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColumnSortModel {
    @JsonProperty("id")
    public String id;
    @JsonProperty("desc")
    public Boolean desc;
}