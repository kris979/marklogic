package com.agisoft.marklogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private DBService service;

    @Autowired
    public Controller(DBService service) {
        this.service = service;
    }

    @GetMapping
    public String get() {
        return service.run();
    }
}
