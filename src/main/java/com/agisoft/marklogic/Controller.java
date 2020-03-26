package com.agisoft.marklogic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class Controller {

    private DBService dbService;

    @Autowired
    public Controller(DBService service) {
        this.dbService = service;
    }

    @PostMapping("/persons")
    public Person add(@RequestBody Person person) {
        dbService.add(person);
        return person;
    }

    @GetMapping("/persons")
    public String get() {
        return dbService.getPerson();
    }

    @GetMapping("/search")
    public String search(@RequestParam String criteria) {
        return dbService.search(criteria);
    }


}
