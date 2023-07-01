package com.kayaspring.kayaspring.DynamicSortAndFilters;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import jakarta.persistence.EntityManager;

public interface IGenericFilterAndSorting<T> {

    public <T> GenericResultClass Apply(EntityManager entityManager, GenericRequestDataClass request, Class<T> tClass);

}