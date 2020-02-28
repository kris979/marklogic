package com.agisoft.marklogic;

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

@Service
@Slf4j
public class DBService {

    public String run() {

        DatabaseClient client = DatabaseClientFactory.newClient(
                "localhost", 8011, new DatabaseClientFactory.DigestAuthContext("admin", "admin"));

        JSONDocumentManager docMgr = client.newJSONDocumentManager();

        // Define a URI value for a document.
        String docId = "/json/person.json";

        Person person = new Person("Kris", "Bliszczak");
        JacksonDatabindHandle<Person> jsonHandle = new JacksonDatabindHandle<Person>(person);


        // Write the document to the database with URI from docId and content from handle
        docMgr.write(docId, jsonHandle);
        log.info("Added {}", person);

        StringHandle value = new StringHandle();
        docMgr.read(docId, value);

        QueryManager queryManager = client.newQueryManager();

        StringQueryDefinition query = queryManager.newStringDefinition();
        query.setCriteria("Bliszczak");

        queryManager.search(query, value);

        log.info("Retrieved {}", value.get());
        return value.get();
    }
}
