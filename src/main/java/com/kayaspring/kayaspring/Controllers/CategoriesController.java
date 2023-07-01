package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Managers.ICategoryManager;
import com.kayaspring.kayaspring.DynamicSortAndFilters.IGenericGetDataWithFilterSortPgn;
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

    @PostMapping("Get")
    public GenericResultClass Get(@RequestBody GenericRequestDataClass requestData) {
        try {
            var result = genericGetDataWithFilterSortPgn.Apply(entityManager, requestData, Category.class);
            return result;
        } catch (Exception e) {
            return GenericResultClass.Error(e, logger);
        }
    }

    @PostMapping("Post")
    public GenericResultClass Post(@RequestBody Category category) {
        try {

            var isDataExists = service.existsById(category.id);
            if (isDataExists) throw new Exception("The record already exist!");

            service.save(category);
            return GenericResultClass.Success(true, 0);
        } catch (Exception e) {
            return GenericResultClass.Error(e, logger);
        }
    }

    @PutMapping("Update")
    public GenericResultClass Update(@RequestBody Category category) {
        try {

            var isDataExists = service.existsById(category.id);
            if (!isDataExists) throw new Exception("The record does not exist!");
            service.save(category);
            return GenericResultClass.Success(true, 0);
        } catch (Exception e) {
            return GenericResultClass.Error(e, logger);
        }
    }

    @DeleteMapping("Delete/{id}")
    public GenericResultClass Delete(@PathVariable("id") long id) {
        try {
            service.deleteById(id);
            return GenericResultClass.Success(true, 0);
        } catch (Exception e) {
            return GenericResultClass.Error(e, logger);
        }
    }


}
