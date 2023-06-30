package com.kayaspring.kayaspring.DynamicSortAndFilters;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class GenericSort {
    public static Sort createSort(List<ColumnSortModel> sorts) {
        List<Sort.Order> orders = new ArrayList<>();
        for (ColumnSortModel sort : sorts) {
            orders.add(sort.desc ? Sort.Order.desc(sort.id) : Sort.Order.asc(sort.id));
        }
        return Sort.by(orders);
    }
}
