package com.agisoft.marklogic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.HttpMethod;

@RestController
@Slf4j
public class Controller {

    private DBService service;
    private RestTemplate restTemplate;


    @Autowired
    public Controller(DBService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/zipkin")
    public String get() {
        log.info("Hello Sleuth");
        return service.run();
    }
}
