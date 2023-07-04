package com.kayaspring.kayaspring.Business.Services;

import com.kayaspring.kayaspring.Api.Middlewares.Logging.ILogger;
import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.DynamicSortAndFilters.IGenericGetDataWithFilterSortPgn;
import com.kayaspring.kayaspring.Data.Repositories.ICategoryRepository;
import com.kayaspring.kayaspring.Entities.Models.Category;
import com.kayaspring.kayaspring.Entities.Models.User.UserDetailsImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class CategoryService {


    private final ICategoryRepository categoryRepository;
    private final IGenericGetDataWithFilterSortPgn genericGetDataWithFilterSortPgn;
    private final AuthenticationService authenticationService;
    private final ILogger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public CategoryService(ICategoryRepository categoryRepository, IGenericGetDataWithFilterSortPgn genericGetDataWithFilterSortPgn,
                           AuthenticationService authenticationService, ILogger logger) {
        this.categoryRepository = categoryRepository;
        this.genericGetDataWithFilterSortPgn = genericGetDataWithFilterSortPgn;
        this.authenticationService = authenticationService;
        this.logger = logger;
    }


    public GenericResultClass getAll() {
        try {
            var data = categoryRepository.findAll();
            return GenericResultClass.Success(data, data.stream().count());
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }

    public GenericResultClass getWithFilterSortPage(@RequestBody GenericRequestDataClass requestData) {
        try {

            UserDetailsImpl currentUser = authenticationService.getCurrentUser();
            var result = genericGetDataWithFilterSortPgn.Apply(entityManager, requestData, Category.class);

            return result;
        } catch (Exception e) {
            return GenericResultClass.Exception(e, logger);
        }
    }

    public GenericResultClass post(@RequestParam String name, @RequestParam int headerId, @RequestParam int subId, @RequestParam MultipartFile imageFile) {
        try {

            UserDetailsImpl currentUser = authenticationService.getCurrentUser();
            Category category = new Category(name, headerId, subId, currentUser.getId());


            if (imageFile != null) {
                MultipartFile image = imageFile;
                byte[] bytes = image.getBytes();
                String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                String uploadDirectory = System.getProperty("user.dir") + "/uploads/" + fileName;
                Path path = Paths.get(uploadDirectory);

                Files.write(path, bytes);
                category.setImagePath(path.toString());
            }

            categoryRepository.save(category);

            return GenericResultClass.Success(true, 0);
        } catch (Exception e) {
            return GenericResultClass.Exception(e, logger);
        }
    }

    public GenericResultClass delete(@PathVariable("id") long id) {
        try {
            categoryRepository.deleteById(id);
            return GenericResultClass.Success(true, 0);
        } catch (Exception e) {
            return GenericResultClass.Exception(e, logger);
        }
    }


}
