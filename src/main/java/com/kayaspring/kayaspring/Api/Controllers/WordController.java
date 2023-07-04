package com.kayaspring.kayaspring.Api.Controllers;

import com.kayaspring.kayaspring.Business.Services.IService;
import com.kayaspring.kayaspring.Business.Services.WordService;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Entities.Models.Word;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/words")
public class WordController {

    private final IService service;

    public WordController(WordService service) {
        this.service = service;
    }

    @GetMapping("getAll")
    public GenericResultClass getAll() {
        return service.getAll();
    }


    @PostMapping("/post")
    public GenericResultClass upsert(@RequestBody Word model) {
        return service.upsert(model);
    }


    @DeleteMapping("delete/{id}")
    public GenericResultClass delete(@PathVariable("id") long id) {
        return service.delete(id);
    }


}
