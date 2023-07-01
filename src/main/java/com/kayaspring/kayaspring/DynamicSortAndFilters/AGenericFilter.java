package com.kayaspring.kayaspring.DynamicSortAndFilters;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public abstract class AGenericFilter<T> {
    public abstract <T> GenericResultClass Apply(EntityManager entityManager, GenericRequestDataClass request, Class<T> tClass);

    public abstract Long CountThePredictedData(TypedQuery typedQuery);

    public abstract <T> List<Order> BuildTheSortingList(ArrayList<ColumnSortModel> sortModels, CriteriaBuilder cb, Root<T> root);

    public abstract <T> List<Predicate> BuildThePredictionList(ArrayList<ColumnFilterModel> filters, CriteriaBuilder cb, Root<T> root);

    public abstract TypedQuery ApplyThePagination(TypedQuery typedQuery, int page, int size);
}
