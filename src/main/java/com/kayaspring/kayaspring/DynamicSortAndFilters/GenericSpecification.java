package com.kayaspring.kayaspring.DynamicSortAndFilters;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {
    private List<ColumnFilterModel> filters;

    public GenericSpecification(List<ColumnFilterModel> filters) {
        this.filters = filters;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (ColumnFilterModel filter : filters) {
            predicates.add(criteriaBuilder.equal(root.get(filter.id), filter.value));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

