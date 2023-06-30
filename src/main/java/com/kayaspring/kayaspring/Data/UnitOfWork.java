package com.kayaspring.kayaspring.Data;


import com.kayaspring.kayaspring.Data.Repos.ICategoriesRepository;
import com.kayaspring.kayaspring.Data.Repos.ILanguagesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope("prototype")
public class UnitOfWork implements IUnitOfWork {

    public UnitOfWork(ILanguagesRepository _languagesRepository, ICategoriesRepository _categoriesRepository) {
        languagesRepository = _languagesRepository;
        categoriesRepository = _categoriesRepository;
    }

    public ILanguagesRepository languagesRepository;
    public ICategoriesRepository categoriesRepository;


    @Override
    public void SaveChanges() {

    }

}
