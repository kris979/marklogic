package com.agisoft.marklogic;

import com.agisoft.marklogic.graphql.types.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class Controller {

    private DBService dbService;

    @Autowired
    public Controller(DBService service, RestTemplate restTemplate) {
        this.dbService = service;
    }

    @PostMapping("/persons")
    public void add(@RequestBody Person person) {
        dbService.add(person);
    }

    @GetMapping("/persons")
    public String get() {
        return dbService.getPerson();
    }


}
