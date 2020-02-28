package com.agisoft.marklogic.graphql.resolvers;

import com.agisoft.marklogic.graphql.types.Human;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class Mutation implements GraphQLMutationResolver {

    public Human createHuman(Map<String,String> createHumanInput) {
        String name = null;
        if (createHumanInput.containsKey("name")) {
            name = createHumanInput.get("name");
        }
        String homePlanet = "Jakku";
        if (createHumanInput.containsKey("homePlanet")) {
            homePlanet = createHumanInput.get("homePlanet");
        }
        return new Human(UUID.randomUUID().toString(), name, null, homePlanet);
    }

}
