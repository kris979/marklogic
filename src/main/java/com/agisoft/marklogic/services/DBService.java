package com.agisoft.marklogic.services;

import com.agisoft.marklogic.model.Person;
import com.agisoft.marklogic.data.JsonFileCardRepository;
import com.agisoft.marklogic.model.Card;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonDatabindHandle;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DBService {

    private static String DOC_ID = "/json/person.json";

    private final DatabaseClient client;
    private final JSONDocumentManager docMgr;
    private final JsonFileCardRepository repository;

    @Autowired
    public DBService(final JsonFileCardRepository repository) {
        this.repository = repository;
        final String localhost = "localhost";
        final int port = 8011;
        final String user = "admin";
        final String password = "admin";
        client = DatabaseClientFactory.newClient(
                localhost, port, new DatabaseClientFactory.DigestAuthContext(user, password));
        docMgr = client.newJSONDocumentManager();
    }

    public void add(Person person) {
        JacksonDatabindHandle<Person> jsonHandle = new JacksonDatabindHandle<>(person);
        docMgr.write(DOC_ID, jsonHandle);
        log.info("Added {}", person);
    }

    public void addCards() {
        final Map<String, List<Card>> stringListMap = repository.groupedByRarity();
        for (Map.Entry<String, List<Card>> entry : stringListMap.entrySet()) {
            final String rarity = entry.getKey();
            final List<Card> cards = entry.getValue();
            for (Card card : cards) {
                final String name = card.getName();
                String docId = rarity + "/" + name.replace(" ", "_");
                JacksonDatabindHandle<Card> handle = new JacksonDatabindHandle<>(card);
                docMgr.write(docId,handle);
            }
        }
    }

    public String getPerson() {
        StringHandle value = new StringHandle();
        docMgr.read(DOC_ID, value);

//        QueryManager queryManager = client.newQueryManager();
//
//        StringQueryDefinition query = queryManager.newStringDefinition();
//        query.setCriteria("Bliszczak");
//
//        queryManager.search(query, value);

        log.info("Retrieved {}", value.get());
        return value.get();
    }

    public String search(String what) {
        StringHandle value = new StringHandle();
        QueryManager queryManager = client.newQueryManager();
        StringQueryDefinition query = queryManager.newStringDefinition();
        query.setCriteria(what);
        queryManager.search(query, value);
        return value.get();
    }
}
