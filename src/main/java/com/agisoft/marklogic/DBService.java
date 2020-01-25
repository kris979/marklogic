package com.agisoft.marklogic;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.TextDocumentManager;
import com.marklogic.client.io.JacksonDatabindHandle;
import com.marklogic.client.io.StringHandle;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    public String run() {

        DatabaseClient client = DatabaseClientFactory.newClient(
                "localhost", 8011, new DatabaseClientFactory.DigestAuthContext("admin", "admin"));

// Make a document manager to work with text files.
        TextDocumentManager docMgr = client.newTextDocumentManager();

// Define a URI value for a document.
        String docId = "/example/text.txt";

        JacksonDatabindHandle<Person> jsonHandle = new JacksonDatabindHandle<Person>(new Person("Kris", "Bliszczak"));


// Write the document to the database with URI from docId and content from handle
        docMgr.write(docId, jsonHandle);

        StringHandle value = new StringHandle();
        docMgr.read(docId, value);

        return value.get();
    }
}
