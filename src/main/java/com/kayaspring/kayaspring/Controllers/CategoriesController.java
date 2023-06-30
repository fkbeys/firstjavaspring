package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.Repos.ICategoriesRepository;
import com.kayaspring.kayaspring.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Api/Categories")
public class CategoriesController {

    private final ICategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesController(ICategoriesRepository categoriesRepository1) {
        this.categoriesRepository = categoriesRepository1;
    }

    @GetMapping
    public GenericResultClass GetCategories() {

        try {
            var data = categoriesRepository.findAll();
            return GenericResultClass.Success(data);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }

    @PostMapping
    public void PostCategories(@RequestBody Category category) {
        categoriesRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public void DeleteCategories(@PathVariable long id) {
        categoriesRepository.deleteById(id);
    }


}
