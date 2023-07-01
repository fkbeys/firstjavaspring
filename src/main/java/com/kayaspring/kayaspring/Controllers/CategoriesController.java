package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.ICategoriesRepository;
import com.kayaspring.kayaspring.DynamicSortAndFilters.ColumnFilterModelSpecification;
import com.kayaspring.kayaspring.Models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Api/Categories")
public class CategoriesController {

    private final ICategoriesRepository service;

    @PersistenceContext
    private EntityManager entityManager;

    public CategoriesController(ICategoriesRepository service) {
        this.service = service;
    }

    @GetMapping("/test")
    public void test() throws Exception {
        var tt = "";
        throw new Exception("Bu bir test istisnasıdır.");
    }


    @PostMapping("Get")
    public GenericResultClass Get(@RequestBody GenericRequestDataClass requestData) {

        try {


            var results = ColumnFilterModelSpecification.filterAndSortEntities(entityManager, requestData, Category.class);
            return GenericResultClass.Success(results);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }


}
