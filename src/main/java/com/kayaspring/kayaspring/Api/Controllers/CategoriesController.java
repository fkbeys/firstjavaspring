package com.kayaspring.kayaspring.Api.Controllers;

import com.kayaspring.kayaspring.Api.Middlewares.Logging.ILogger;
import com.kayaspring.kayaspring.Auth.AuthenticationService;
import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.DynamicSortAndFilters.IGenericGetDataWithFilterSortPgn;
import com.kayaspring.kayaspring.Data.Repositories.ICategoryRepository;
import com.kayaspring.kayaspring.Entities.Models.Category;
import com.kayaspring.kayaspring.Entities.Models.User.UserDetailsImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("api/categories")
public class CategoriesController {

    private final ICategoryRepository service;
    private final IGenericGetDataWithFilterSortPgn genericGetDataWithFilterSortPgn;
    private final ILogger logger;

    private final AuthenticationService authenticationService;

    @PersistenceContext
    private EntityManager entityManager;

    public CategoriesController(ICategoryRepository service, IGenericGetDataWithFilterSortPgn genericFilterAndSorting, ILogger logger, AuthenticationService authenticationService) {
        this.service = service;
        this.genericGetDataWithFilterSortPgn = genericFilterAndSorting;
        this.logger = logger;

        this.authenticationService = authenticationService;
    }

    @PostMapping("get")
    public GenericResultClass Get(@RequestBody GenericRequestDataClass requestData) {
        try {

            UserDetailsImpl currentUser = authenticationService.getCurrentUser();
            var result = genericGetDataWithFilterSortPgn.Apply(entityManager, requestData, Category.class);

            return result;
        } catch (Exception e) {
            return GenericResultClass.Exception(e, logger);
        }
    }

    @PostMapping("post")
    public GenericResultClass Post(@RequestBody Category category) {
        try {

            UserDetailsImpl currentUser = authenticationService.getCurrentUser();
            category.createUser = currentUser.getId();
            var isDataExists = service.existsById(category.getId());
            if (isDataExists) return GenericResultClass.UnSuccessful("The record already exist!");
            service.save(category);
            return GenericResultClass.Success(true, 0);
        } catch (Exception e) {
            return GenericResultClass.Exception(e, logger);
        }
    }

    @PutMapping("update")
    public GenericResultClass Update(@RequestBody Category category) {
        try {

            var isDataExists = service.existsById(category.getId());
            if (!isDataExists) return GenericResultClass.UnSuccessful("The record does not exist!");
            service.save(category);
            return GenericResultClass.Success(true, 0);
        } catch (Exception e) {
            return GenericResultClass.Exception(e, logger);
        }
    }

    @DeleteMapping("delete/{id}")
    public GenericResultClass Delete(@PathVariable("id") long id) {
        try {
            service.deleteById(id);
            return GenericResultClass.Success(true, 0);
        } catch (Exception e) {
            return GenericResultClass.Exception(e, logger);
        }
    }


}
