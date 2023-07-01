package com.kayaspring.kayaspring.DynamicSortAndFilters;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ColumnFilterModelSpecification<T> {

    public static <T> List<T> filterEntities(EntityManager entityManager, List<ColumnFilterModel> filters, Class<T> tClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(tClass);
        Root<T> root = query.from(tClass);

        List<Predicate> predicates = new ArrayList<>();

        for (ColumnFilterModel filter : filters) {
            Path<Object> field = root.get(filter.id);
            Class<?> fieldType = field.getJavaType();

            if (fieldType.equals(String.class)) {
                predicates.add(cb.like(cb.lower(field.as(String.class)), "%" + filter.value.toString().toLowerCase() + "%"));
            } else if (fieldType.equals(Integer.class) || fieldType.equals(Double.class)) {
                // Parse the string to get the list of values
                String[] values = filter.value.toString().replaceAll("\\[|\\]|\"", "").split(",");
                // Convert the strings to double

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

                // Parse the string to get the list of dates
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


        query.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }



}
