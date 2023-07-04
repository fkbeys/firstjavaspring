package com.kayaspring.kayaspring.Business.Services;

import com.kayaspring.kayaspring.Common.GenericResultClass;

public interface IService<T> {

    public GenericResultClass getAll();
    public GenericResultClass upsert(T model);
    public GenericResultClass delete(long id);

}
