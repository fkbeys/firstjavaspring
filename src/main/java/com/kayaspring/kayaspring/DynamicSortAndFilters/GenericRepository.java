package com.kayaspring.kayaspring.DynamicSortAndFilters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;



public interface GenericRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
