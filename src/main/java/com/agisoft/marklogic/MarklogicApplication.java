package com.agisoft.marklogic;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@SpringBootApplication
@Configuration
public class MarklogicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarklogicApplication.class, args);
    }

}
