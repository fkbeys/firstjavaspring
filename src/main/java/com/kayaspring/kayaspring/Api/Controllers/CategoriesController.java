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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RestController

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


    @PostMapping("/post")
    public GenericResultClass post(@RequestParam String name, @RequestParam int headerId, @RequestParam int subId, @RequestParam("image") Optional<MultipartFile> imageOptional) {
        try {

            UserDetailsImpl currentUser = authenticationService.getCurrentUser();
            Category category = new Category(name, headerId, subId,currentUser.getId());


            if (imageOptional.isPresent()) {
                MultipartFile image = imageOptional.get();
                byte[] bytes = image.getBytes();
                String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                String uploadDirectory = System.getProperty("user.dir") + "/uploads/" + fileName;
                Path path = Paths.get(uploadDirectory);

                Files.write(path, bytes);
                category.setImagePath(path.toString());
            }
            if (category.getId() == 0) {
                category.setId(0L);
            }

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
