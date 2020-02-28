package com.agisoft.marklogic.graphql.resolvers;

import com.agisoft.marklogic.graphql.CharacterRepository;
import com.agisoft.marklogic.graphql.types.Droid;
import com.agisoft.marklogic.graphql.types.Episode;
import com.agisoft.marklogic.graphql.types.Human;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private CharacterRepository characterRepository;

    public com.agisoft.marklogic.graphql.types.Character hero(Episode episode) {
        return episode != null ? characterRepository.getHeroes().get(episode) : characterRepository.getCharacters().get("1000");
    }

    public Human human(String id, DataFetchingEnvironment env) {
        return (Human) characterRepository.getCharacters().values().stream()
            .filter(character -> character instanceof Human && character.getId().equals(id))
            .findFirst()
            .orElseGet(null);
    }

    public Droid droid(String id) {
        return (Droid) characterRepository.getCharacters().values().stream()
            .filter(character -> character instanceof Droid && character.getId().equals(id))
            .findFirst()
            .orElseGet(null);
    }

    public com.agisoft.marklogic.graphql.types.Character character(String id) {
        return characterRepository.getCharacters().get(id);
    }
}
