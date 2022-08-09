package com.bootninza.droolsdemo;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DroolsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroolsDemoApplication.class, args);
    }


}
