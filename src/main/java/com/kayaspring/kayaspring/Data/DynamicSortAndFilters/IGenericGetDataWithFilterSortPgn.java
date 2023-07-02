package com.kayaspring.kayaspring.Data.DynamicSortAndFilters;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import jakarta.persistence.EntityManager;


public interface IGenericGetDataWithFilterSortPgn  {

    public <T> GenericResultClass Apply(EntityManager entityManager, GenericRequestDataClass request, Class<T> tClass);

}