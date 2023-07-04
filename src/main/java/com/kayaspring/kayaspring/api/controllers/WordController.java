package com.kayaspring.kayaspring.api.controllers;

import com.kayaspring.kayaspring.business.services.IService;
import com.kayaspring.kayaspring.business.services.WordService;
import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.entities.models.Word;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("api/words")
public class WordController {

    private final IService<Word> service;

    public WordController(WordService service) {
        this.service = service;
    }

    @GetMapping("getAll")
    public GenericResultClass getAll() {
        return service.getAll();
    }


    @PostMapping("/post")
    public GenericResultClass upsert(@Valid @RequestBody Word model) {
        return service.upsert(model);
    }


    @DeleteMapping("delete/{id}")
    public GenericResultClass delete(@Valid @PathVariable("id") long id) {
        return service.delete(id);
    }


}
