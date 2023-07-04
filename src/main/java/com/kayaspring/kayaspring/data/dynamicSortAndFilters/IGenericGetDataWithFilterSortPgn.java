package com.kayaspring.kayaspring.data.dynamicSortAndFilters;

import com.kayaspring.kayaspring.common.GenericRequestDataClass;
import com.kayaspring.kayaspring.common.GenericResultClass;
import jakarta.persistence.EntityManager;


public interface IGenericGetDataWithFilterSortPgn  {

    public <T> GenericResultClass Apply(EntityManager entityManager, GenericRequestDataClass request, Class<T> tClass);

}