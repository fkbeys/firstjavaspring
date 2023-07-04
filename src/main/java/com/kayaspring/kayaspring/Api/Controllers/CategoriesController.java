package com.kayaspring.kayaspring.Api.Controllers;

import com.kayaspring.kayaspring.Business.Services.CategoryService;
import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {

    private final CategoryService service;

    public CategoriesController(CategoryService service) {
        this.service = service;
    }


    @GetMapping("getAll")
    public GenericResultClass getAll() {
        return service.getAll();
    }


    @PostMapping("getWithFilterSortPage")
    public GenericResultClass getWithFilterSortPage(@Valid @RequestBody GenericRequestDataClass requestData) {
        return service.getWithFilterSortPage(requestData);
    }


    @PostMapping("/post")
    public GenericResultClass upsert(@Valid @RequestParam String name, @RequestParam int headerId, @RequestParam int subId, @RequestParam MultipartFile imageFile) {
        return service.post(name, headerId, subId, imageFile);
    }


    @DeleteMapping("delete/{id}")
    public GenericResultClass delete(@Valid @PathVariable("id") long id) {
        return service.delete(id);
    }


}
