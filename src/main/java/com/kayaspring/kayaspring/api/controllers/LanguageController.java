package com.kayaspring.kayaspring.api.controllers;

import com.kayaspring.kayaspring.Business.Services.IService;
import com.kayaspring.kayaspring.Business.Services.LanguageService;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Entities.Models.Language;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/languages")
public class LanguageController {

    private final IService<Language> service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping("getAll")
    public GenericResultClass getAll() {
        return service.getAll();
    }

    @PostMapping("/post")
    public GenericResultClass upsert(@Valid  @RequestBody Language model) {
        return service.upsert(model);
    }


    @DeleteMapping("delete/{id}")
    public GenericResultClass delete(@Valid @PathVariable("id") long id) {
        return service.delete(id);
    }


}
