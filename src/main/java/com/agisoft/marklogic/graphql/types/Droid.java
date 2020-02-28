package com.agisoft.marklogic.graphql.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Droid implements com.agisoft.marklogic.graphql.types.Character {
    private String id;
    private String name;
    private List<Character> friends = new ArrayList<>();
    private List<Episode> appearsIn;
    private String primaryFunction;

    public Droid(String id, String name, List<Episode> appearsIn, String primaryFunction) {
        this.id = id;
        this.name = name;
        this.appearsIn = appearsIn;
        this.primaryFunction = primaryFunction;
    }

    public void addFriends(Character ... friends) {
        this.friends.addAll(Arrays.asList(friends));
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Character> getFriends() {
        return friends;
    }

    @Override
    public List<Episode> getAppearsIn() {
        return appearsIn;
    }

    public String getPrimaryFunction() {
        return primaryFunction;
    }
}
