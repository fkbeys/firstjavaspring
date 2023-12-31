package com.kayaspring.kayaspring.api.controllers;

import com.kayaspring.kayaspring.business.services.LanguageService;
import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.entities.models.Language;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/languages")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping("getAll")
    public GenericResultClass getAll() {
        return service.getAll();
    }

    @PostMapping("/post")
    public GenericResultClass upsert(@Valid @RequestBody Language model) {
        return service.upsert(model);
    }


    @DeleteMapping("delete/{id}")
    public GenericResultClass delete(@Valid @PathVariable("id") long id) {
        return service.delete(id);
    }


    @PostMapping("chooseMainAndTargetLanguages/{mainId}/{targetId}")
    public GenericResultClass chooseMainAndTargetLanguages(@Valid @PathVariable long mainId, @PathVariable long targetId) {
        return service.chooseMainAndTargetLanguages(mainId, targetId);
    }



}
