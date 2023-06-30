package com.kayaspring.kayaspring.Controllers;

import com.kayaspring.kayaspring.Common.GenericRequestDataClass;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.DynamicSortAndFilters.GenericService;
import com.kayaspring.kayaspring.Models.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Api/Categories")
public class CategoriesController {

    private final GenericService<Category> service;

    public CategoriesController(GenericService<Category> service) {
        this.service = service;
    }


    @PostMapping("Get")
    public GenericResultClass Get(@RequestBody GenericRequestDataClass requestData) {

        try {
            var filterList = requestData.getColumnFilterList();
            var sortList = requestData.getColumnSortList();

            var data = service.findWithFiltersAndSort(requestData);

            return GenericResultClass.Success(data);
        } catch (Exception e) {
            return GenericResultClass.Error(e);
        }
    }


}
