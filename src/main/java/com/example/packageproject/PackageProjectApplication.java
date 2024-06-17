package com.example.packageproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"com.example.packageproject"})
public class PackageProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageProjectApplication.class, args);
    }

}
