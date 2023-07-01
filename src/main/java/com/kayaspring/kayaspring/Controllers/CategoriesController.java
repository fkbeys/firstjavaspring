package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.ICategoryManager;
import com.kayaspring.kayaspring.DynamicSortAndFilters.IGenericGetDataWithFilterSortPgn;
import com.kayaspring.kayaspring.Middlewares.Logging.ILogger;
import com.kayaspring.kayaspring.Models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("Api/Categories")
public class CategoriesController {

    private final ICategoryManager service;
    private final IGenericGetDataWithFilterSortPgn genericGetDataWithFilterSortPgn;
    private final ILogger logger;
    @PersistenceContext
    private EntityManager entityManager;

    public CategoriesController(ICategoryManager service, IGenericGetDataWithFilterSortPgn genericFilterAndSorting, ILogger logger) {
        this.service = service;
        this.genericGetDataWithFilterSortPgn = genericFilterAndSorting;
        this.logger = logger;
    }

    @PostMapping("GetCategories")
    public GenericResultClass Get(@RequestBody GenericRequestDataClass requestData) {
        try {
           var result = genericGetDataWithFilterSortPgn.Apply(entityManager, requestData, Category.class);
            return result;
        } catch (Exception e) {
            return GenericResultClass.Error(e, logger);
        }
    }
}
