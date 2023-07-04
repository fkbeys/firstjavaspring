package com.kayaspring.kayaspring.data.dynamicSortAndFilters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kayaspring.kayaspring.api.middlewares.logging.ILogger;
import com.kayaspring.kayaspring.common.GenericRequestDataClass;
import com.kayaspring.kayaspring.common.GenericResultClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class GenericGetDataWithFilterSortPgn<T> implements IGenericGetDataWithFilterSortPgn {

    private final ILogger logger;

    public GenericGetDataWithFilterSortPgn(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public GenericResultClass Apply(EntityManager entityManager, GenericRequestDataClass request, Class tClass) {
        List<ColumnFilterModel> filters = getColumnFilterList(request.columnFilters);
        List<ColumnSortModel> sortModels = getColumnSortList(request.columnSorts);
        int page = request.page;
        int size = request.size;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(tClass);
        Root<T> root = query.from(tClass);

        List<Predicate> predicates = BuildThePredictionList(filters, cb, root);
        List<Order> orders = BuildTheSortingList(sortModels, cb, root);

        query.select(root);
        if (predicates.size() > 0) {
            query.where(predicates.toArray(new Predicate[0]));
        }
        if (orders.size() > 0) {
            query.orderBy(orders);
        }

        TypedQuery<T> typedQuery = entityManager.createQuery(query);

        //TODO: Performance issue with this method. Needs to be changed as soon as possible.
        Long count = CountThePredictedData(typedQuery);

        ApplyThePagination(typedQuery, page, size);

        var data = typedQuery.getResultList();
        return GenericResultClass.Success(data, count);
    }

    private List<ColumnFilterModel> getColumnFilterList(String columnFilters) {
        try {
            return jsonStringToList(columnFilters, new TypeReference<List<ColumnFilterModel>>() {
            });
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private List<ColumnSortModel> getColumnSortList(String columnSorts) {
        try {
            return jsonStringToList(columnSorts, new TypeReference<List<ColumnSortModel>>() {
            });
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private <T> List<T> jsonStringToList(String jsonString, TypeReference<List<T>> typeReference) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            logger.log("JsonError", "JSON stringi parse error.");
            return Collections.emptyList();
        }
    }

    private Long CountThePredictedData(TypedQuery<T> typedQuery) {
        Long count = typedQuery.getResultStream().count();
        return count;
    }

    private <T> List<Order> BuildTheSortingList(List<ColumnSortModel> sortModels, CriteriaBuilder cb, Root<T> root) {
        List<Order> orders = new ArrayList<>();
        if (sortModels == null) return orders;

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

    private <T> List<Predicate> BuildThePredictionList(List<ColumnFilterModel> filters, CriteriaBuilder cb, Root<T> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (filters == null) return predicates;

        for (ColumnFilterModel filter : filters) {
            Path<Object> field = root.get(filter.id);
            Class<?> fieldType = field.getJavaType();

            if (fieldType.equals(String.class)) {
                predicates.add(buildStringPredicate(cb, field, filter));
            } else if (fieldType.equals(Integer.class) || fieldType.equals(Double.class)) {
                predicates.add(buildNumericPredicate(cb, root, filter));
            } else if (fieldType.equals(Boolean.class)) {
                predicates.add(buildBooleanPredicate(cb, root, filter));
            } else if (fieldType.equals(LocalDateTime.class)) {
                predicates.add(buildDateTimePredicate(cb, root, filter));
            }
        }
        return predicates;
    }

    private Predicate buildStringPredicate(CriteriaBuilder cb, Path<Object> field, ColumnFilterModel filter) {
        return cb.like(cb.lower(field.as(String.class)), "%" + filter.value.toString().toLowerCase() + "%");
    }

    private <T> Predicate buildNumericPredicate(CriteriaBuilder cb, Root<T> root, ColumnFilterModel filter) {
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
        return cb.between(root.get(filter.id).as(Double.class), minValue, maxValue);
    }

    private <T> Predicate buildBooleanPredicate(CriteriaBuilder cb, Root<T> root, ColumnFilterModel filter) {
        return cb.equal(root.get(filter.id), Boolean.parseBoolean(filter.value.toString()));
    }

    private <T> Predicate buildDateTimePredicate(CriteriaBuilder cb, Root<T> root, ColumnFilterModel filter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] values = filter.value.toString().replaceAll("[\\[\\]\",]", "").split(",");


        LocalDate startDate;
        LocalDate endDate;
        if (values.length > 1) {
            startDate = LocalDate.parse(values[0], formatter);
            endDate = LocalDate.parse(values[1], formatter);
        } else {
            startDate = LocalDate.MIN;
            endDate = LocalDate.parse(values[0], formatter);
        }
        return cb.between(root.get(filter.id).as(LocalDate.class), startDate, endDate);
    }

    private TypedQuery<T> ApplyThePagination(TypedQuery<T> typedQuery, int page, int size) {
        typedQuery.setFirstResult(page * size);
        typedQuery.setMaxResults(size);
        return typedQuery;
    }
}
