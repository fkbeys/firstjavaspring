package com.kayaspring.kayaspring.data.dynamicSortAndFilters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColumnFilterModel {
    @JsonProperty("id")
    public String id;
    @JsonProperty("value")
    public Object value;
}