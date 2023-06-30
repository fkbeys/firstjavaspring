package com.kayaspring.kayaspring.Common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kayaspring.kayaspring.DynamicSortAndFilters.ColumnFilterModel;
import com.kayaspring.kayaspring.DynamicSortAndFilters.ColumnSortModel;

import java.io.IOException;
import java.util.List;

public class GenericRequestDataClass {
    public int page;
    public int size;
    public String columnFilters;
    public String columnSorts;

    public List<ColumnFilterModel> getColumnFilterList() {
        return jsonStringToList(columnFilters, new TypeReference<List<ColumnFilterModel>>() {
        });
    }

    public List<ColumnSortModel> getColumnSortList() {
        return jsonStringToList(columnSorts, new TypeReference<List<ColumnSortModel>>() {
        });
    }

    private <T> List<T> jsonStringToList(String jsonString, TypeReference<List<T>> typeReference) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("JSON stringi parse error.", e);
        }
    }
}
