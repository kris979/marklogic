package com.agisoft.marklogic;

import com.agisoft.marklogic.data.CardFileReader;
import com.agisoft.marklogic.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class Controller {

    private DBService dbService;
    private CardFileReader cardFileReader;

    @Autowired
    public Controller(DBService service, CardFileReader cardFileReader) {
        this.dbService = service;
        this.cardFileReader = cardFileReader;
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

    @GetMapping("/cards")
    public List<Card> getClassicCards() {
        return cardFileReader.getCards();
    }

    @GetMapping("/addcards")
    public void addCards() {
         dbService.addCards();
    }

}
