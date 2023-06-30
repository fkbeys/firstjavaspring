package com.kayaspring.kayaspring.Managers;

import com.kayaspring.kayaspring.Common.GenericResultClass;

import java.beans.Expression;

public interface IManager<T> {

    public GenericResultClass add(T entity);
    public GenericResultClass getListByFilter(String columnFilter,String columnSort);
    public GenericResultClass getFirstOrDefault(Expression filter);
    public GenericResultClass update(T entity);
    public GenericResultClass delete(long id);


}
