package com.agisoft.marklogic;

import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    GraphQLSchema schema() {
//        return GraphQLSchema.newSchema()
//                            .query(GraphQLObjectType.newObject()
//                                                    .name("query")
//                                                    .field(field -> field
//                                                            .name("test")
//                                                            .type(Scalars.GraphQLString)
//                                                            .dataFetcher(environment -> "response")
//                                                    )
//                                                    .field(field -> field.name("kris").type(Scalars.GraphQLString).dataFetcher(environment -> "Bliszczak"))
//                                                    .build())
//                            .build();
//    }
}
