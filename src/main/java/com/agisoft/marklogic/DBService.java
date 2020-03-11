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

    private String localhost = "localhost";
    private int port = 8011;
    private String user = "admin";
    private String password = "admin";

    DatabaseClient client;
    JSONDocumentManager docMgr;

    // Define a URI value for a document.
    String docId = "/json/person.json";

    @Autowired
    public DBService() {
        client = DatabaseClientFactory.newClient(
                localhost, port, new DatabaseClientFactory.DigestAuthContext(user, password));
        docMgr = client.newJSONDocumentManager();
    }

    public void add(Person person) {

        JacksonDatabindHandle<Person> jsonHandle = new JacksonDatabindHandle<>(person);

        // Write the document to the database with URI from docId and content from handle
        docMgr.write(docId, jsonHandle);

        log.info("Added {}", person);
    }


    public String getPerson() {
        StringHandle value = new StringHandle();
        docMgr.read(docId, value);

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
