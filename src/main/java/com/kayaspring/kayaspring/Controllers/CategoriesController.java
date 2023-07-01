package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.ICategoriesRepository;
import com.kayaspring.kayaspring.DynamicSortAndFilters.ColumnFilterModelSpecification;
import com.kayaspring.kayaspring.Models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Api/Categories")
public class CategoriesController {

    private final ICategoriesRepository service;

    public CategoriesController(ICategoriesRepository service) {
        this.service = service;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("Get")
    public GenericResultClass Get(@RequestBody GenericRequestDataClass requestData) {

        try {
            var filterList = requestData.getColumnFilterList();
            var sortList = requestData.getColumnSortList();

            var results = ColumnFilterModelSpecification.filterEntities(entityManager, filterList, Category.class);

            return GenericResultClass.Success(results);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }


}
