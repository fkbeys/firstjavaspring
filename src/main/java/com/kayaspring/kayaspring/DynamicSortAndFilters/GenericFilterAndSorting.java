package com.kayaspring.kayaspring.DynamicSortAndFilters;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class GenericFilterAndSorting<T> {

    public <T> GenericResultClass Apply(EntityManager entityManager, GenericRequestDataClass request, Class<T> tClass) {

        List<ColumnFilterModel> filters = request.getColumnFilterList();
        List<ColumnSortModel> sortModels = request.getColumnSortList();
        int page = request.page;
        int size = request.size;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(tClass);
        Root<T> root = query.from(tClass);

        List<Predicate> predicates = BuildThePredictionList(filters, cb, root);
        List<Order> orders = BuildTheSortingList(sortModels, cb, root);

        query.select(root).where(predicates.toArray(new Predicate[0])).orderBy(orders);

        TypedQuery<T> typedQuery = entityManager.createQuery(query);

        //TODO: Performance issue with this method. Needs to be changed as soon as possible.
        Long count = CountThePredictedData(typedQuery);

        typedQuery = ApplyThePagination(typedQuery, page, size);

        var data = typedQuery.getResultList();
        return GenericResultClass.Success(data, count);
    }


    public Long CountThePredictedData(TypedQuery typedQuery) {
        Long count = typedQuery.getResultStream().count();

//        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
//        Root<T> countRoot = countQuery.from(tClass);
//        countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
//        count = entityManager.createQuery(countQuery).getSingleResult();
        return count;
    }

    public <T> List<Order> BuildTheSortingList(List<ColumnSortModel> sortModels, CriteriaBuilder cb, Root<T> root) {
        List<Order> orders = new ArrayList<>();
        for (ColumnSortModel sortModel : sortModels) {
            Path<Object> field = root.get(sortModel.id);
            if (sortModel.desc) {
                orders.add(cb.desc(field));
            } else {
                orders.add(cb.asc(field));
            }
        }
        return orders;
    }

    public <T> List<Predicate> BuildThePredictionList(List<ColumnFilterModel> filters, CriteriaBuilder cb, Root<T> root) {

        List<Predicate> predicates = new ArrayList<>();

        for (ColumnFilterModel filter : filters) {
            Path<Object> field = root.get(filter.id);
            Class<?> fieldType = field.getJavaType();

            if (fieldType.equals(String.class)) {
                predicates.add(cb.like(cb.lower(field.as(String.class)), "%" + filter.value.toString().toLowerCase() + "%"));
            } else if (fieldType.equals(Integer.class) || fieldType.equals(Double.class)) {

                String[] values = filter.value.toString().replaceAll("\\[|\\]|\"", "").split(",");

                Double minValue;
                Double maxValue;
                if (values.length > 1) {
                    minValue = Double.parseDouble(values[0]);
                    maxValue = Double.parseDouble(values[1]);
                } else {
                    minValue = 0.0;
                    maxValue = Double.parseDouble(values[0]);
                }

                predicates.add(cb.between(root.get(filter.id).as(Double.class), minValue, maxValue));
            } else if (fieldType.equals(Boolean.class)) {
                predicates.add(cb.equal(root.get(filter.id), Boolean.parseBoolean(filter.value.toString())));
            } else if (fieldType.equals(LocalDateTime.class)) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String[] values = filter.value.toString().replaceAll("\\[|\\]|\"", "").split(",");

                LocalDate startDate;
                LocalDate endDate;

                if (values.length > 1) {
                    startDate = LocalDate.parse(values[0], formatter);
                    endDate = LocalDate.parse(values[1], formatter);
                } else {
                    startDate = LocalDate.MIN;
                    endDate = LocalDate.parse(values[0], formatter);
                }
                predicates.add(cb.between(root.get(filter.id).as(LocalDate.class), startDate, endDate));
            }
        }
        return predicates;
    }


    public TypedQuery ApplyThePagination(TypedQuery typedQuery, int page, int size) {
        typedQuery.setFirstResult(page * size);
        typedQuery.setMaxResults(size);
        return typedQuery;
    }


}
