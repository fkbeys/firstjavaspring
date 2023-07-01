package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.ICategoriesRepository;
import com.kayaspring.kayaspring.DynamicSortAndFilters.GenericFilterAndSorting;
import com.kayaspring.kayaspring.Middlewares.Logging.ILogger;
import com.kayaspring.kayaspring.Models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Api/Categories")
public class CategoriesController {

    private final ICategoriesRepository service;
    private final ILogger logger;
    @PersistenceContext
    private EntityManager entityManager;

    public CategoriesController(ICategoriesRepository service, ILogger logger) {
        this.service = service;
        this.logger = logger;
    }

    @GetMapping("test")
    public void test() throws Exception {
        throw new Exception("Error test");
    }

    @PostMapping("Get")
    public GenericResultClass Get(@RequestBody GenericRequestDataClass requestData) {
        try {
            var results = GenericFilterAndSorting.apply(entityManager, requestData, Category.class);
            return GenericResultClass.Success(results);
        } catch (Exception e) {
            return GenericResultClass.Error(e,logger);
        }
    }


}
