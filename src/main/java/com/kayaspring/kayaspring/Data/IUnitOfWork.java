package com.kayaspring.kayaspring.Data;

import com.kayaspring.kayaspring.Data.Repos.ICategoriesRepository;
import com.kayaspring.kayaspring.Data.Repos.ILanguagesRepository;

public interface IUnitOfWork {


    void SaveChanges();

}
