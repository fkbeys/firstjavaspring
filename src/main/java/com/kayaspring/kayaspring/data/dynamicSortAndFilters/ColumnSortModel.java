package com.kayaspring.kayaspring.data.dynamicSortAndFilters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColumnSortModel {
    @JsonProperty("id")
    public String id;
    @JsonProperty("desc")
    public Boolean desc;
}