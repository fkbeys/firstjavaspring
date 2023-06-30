package com.kayaspring.kayaspring.DynamicSortAndFilters;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class GenericService<T> {
    private final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> findWithFiltersAndSort(GenericRequestDataClass requestData) {
        GenericSpecification<T> spec = new GenericSpecification<>(requestData.getColumnFilterList());
        Pageable pageable = PageRequest.of(requestData.page, requestData.size, GenericSort.createSort(requestData.getColumnSortList()));
        return repository.findAll(spec, pageable);
    }
}
