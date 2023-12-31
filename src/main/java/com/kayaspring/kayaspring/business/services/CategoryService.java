package com.kayaspring.kayaspring.business.services;

import com.kayaspring.kayaspring.api.middlewares.logging.ILogger;
import com.kayaspring.kayaspring.common.GenericRequestDataClass;
import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.data.dynamicSortAndFilters.IGenericGetDataWithFilterSortPgn;
import com.kayaspring.kayaspring.data.repositories.ICategoryRepository;
import com.kayaspring.kayaspring.data.repositories.IUserRepository;
import com.kayaspring.kayaspring.entities.models.Category;
import com.kayaspring.kayaspring.entities.models.User.AppUser;
import com.kayaspring.kayaspring.entities.models.User.UserDetailsImpl;
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
    private final IUserRepository userRepository;
    private final IGenericGetDataWithFilterSortPgn genericGetDataWithFilterSortPgn;
    private final AuthenticationService authenticationService;
    private final ILogger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public CategoryService(ICategoryRepository categoryRepository, IUserRepository userRepository, IGenericGetDataWithFilterSortPgn genericGetDataWithFilterSortPgn,
                           AuthenticationService authenticationService, ILogger logger) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
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

            UserDetailsImpl currentUser = authenticationService.getCurrentUserImpl();
            var result = genericGetDataWithFilterSortPgn.Apply(entityManager, requestData, Category.class);

            return result;
        } catch (Exception e) {
            return GenericResultClass.Exception(e, logger);
        }
    }

    public GenericResultClass post(@RequestParam String name, @RequestParam int headerId, @RequestParam int subId, @RequestParam MultipartFile imageFile) {
        try {

            UserDetailsImpl currentUser = authenticationService.getCurrentUserImpl();
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


    public GenericResultClass AddCategoryToUsersFavoritCategories(long cateogryId) {
        try {

            Category category = categoryRepository.findById(cateogryId).orElseThrow();

            String currentUserName = authenticationService.getUserName();
            AppUser user = userRepository.findByUsername(currentUserName);
            var currentCategoriesList = user.getCategories();
            var isExist = currentCategoriesList.contains(category);
            if (!isExist) {
                currentCategoriesList.add(category);
                user.setUserCategories(currentCategoriesList);
                userRepository.save(user);
                return GenericResultClass.Success(true, 1);
            } else return GenericResultClass.UnSuccessful("Category already exists in users categories list...");

        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }

    }


}
