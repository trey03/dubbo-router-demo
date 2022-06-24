package com.jaffa.practice.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jaffa.practice.dubbo")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).registerShutdownHook();
    }
}
