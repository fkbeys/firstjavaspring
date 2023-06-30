package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.IUnitOfWork;
import com.kayaspring.kayaspring.Data.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Api/Categories")
public class CategoriesController {

    private final UnitOfWork uof;

    @Autowired
    public CategoriesController(IUnitOfWork uof) {
        this.uof = (UnitOfWork) uof;
    }

    @GetMapping
    public int GetCategories() {

       try {
           var ddd=uof.categoriesRepository.findAll();
       }
       catch (Exception e){
           System.out.println(e.getMessage());
       }


        return 11;
    }

    @PostMapping
    public GenericResultClass PostCategories() {

        return GenericResultClass.Success(111);
    }


}
