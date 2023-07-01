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

}
