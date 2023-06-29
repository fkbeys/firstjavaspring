package com.kayaspring.kayaspring;

import com.kayaspring.Common.GenericResultClass;
import com.kayaspring.Models.Word;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KayaspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(KayaspringApplication.class, args);
    }

    @GetMapping
    public GenericResultClass naber() {

        return GenericResultClass.Success(111);
    }


}
