package com.kayaspring.kayaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kayaspring")

public class KayaspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(KayaspringApplication.class, args);
    }

}
