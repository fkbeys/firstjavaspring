package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.ICategoriesRepository;
import com.kayaspring.kayaspring.DynamicSortAndFilters.GenericFilterAndSorting;
import com.kayaspring.kayaspring.DynamicSortAndFilters.IGenericFilterAndSorting;
import com.kayaspring.kayaspring.Middlewares.Logging.ILogger;
import com.kayaspring.kayaspring.Models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
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

    @PostMapping("GetCategories")
    public GenericResultClass Get(@RequestBody GenericRequestDataClass requestData) {
        try {
            IGenericFilterAndSorting dataGetAndFilter = new GenericFilterAndSorting();
            var result = dataGetAndFilter.Apply(entityManager, requestData, Category.class);
            return result;
        } catch (Exception e) {
            return GenericResultClass.Error(e, logger);
        }
    }




}
