package com.kayaspring.kayaspring.Data.DynamicSortAndFilters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColumnFilterModel {
    @JsonProperty("id")
    public String id;
    @JsonProperty("value")
    public Object value;
}