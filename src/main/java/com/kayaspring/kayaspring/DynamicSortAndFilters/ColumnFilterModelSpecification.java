package com.kayaspring.kayaspring.DynamicSortAndFilters;

import com.kayaspring.kayaspring.Models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class ColumnFilterModelSpecification<T> {

    public static List<Category> filterCategories(EntityManager entityManager, List<ColumnFilterModel> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = cb.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);

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
            }
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }


}
