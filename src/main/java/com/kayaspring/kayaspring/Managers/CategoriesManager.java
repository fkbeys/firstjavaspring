package com.kayaspring.kayaspring.Managers;

import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.ICategoriesRepository;
import com.kayaspring.kayaspring.Models.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.beans.Expression;
import java.util.List;


@Transactional
@Service
public class CategoriesManager implements IManager<Category> {
    private final ICategoriesRepository categoriesRepository;

    public CategoriesManager(ICategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    @Override
    public GenericResultClass add(Category entity) {

        try {
            categoriesRepository.save(entity);
            return GenericResultClass.Success(true);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }

    @Override
    public GenericResultClass getListByFilter(String columnFilter, String columnSort) {
        try {

            var data="";

            return GenericResultClass.Success(data);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }

    @Override
    public GenericResultClass getFirstOrDefault(Expression filter) {
        try {
            //   categoriesRepository.save(entity);
            return GenericResultClass.Success(true);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }

    @Override
    public GenericResultClass update(Category entity) {
        try {
            //   categoriesRepository.save(entity);
            return GenericResultClass.Success(true);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }

    @Override
    public GenericResultClass delete(long id) {
        try {
            categoriesRepository.deleteById(id);
            return GenericResultClass.Success(true);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }
}
