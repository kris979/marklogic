package com.agisoft.marklogic.data;

import com.agisoft.marklogic.model.Card;
import com.agisoft.marklogic.model.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Repository
public class JsonFileCardRepository {

    private final List<Card> cards;

    @Autowired
    public JsonFileCardRepository(CardFileReader parser) {
        super();
        cards = parser.getCards();
    }

    public List<Card> getMinions() {
        return cards.stream().filter(c -> c.getType() != null && c.getType().equals(CardType.MINION)).collect(toList());
    }

    public List<Card> getSpells() {
        return cards.stream().filter(c -> c.getType() != null && c.getType().equals(CardType.SPELL)).collect(toList());
    }

    public List<Card> getWeapons() {
        return cards.stream().filter(c -> c.getType() != null && c.getType().equals(CardType.WEAPON)).collect(toList());
    }

    public List<Card> getHeros() {
        return cards.stream().filter(c -> c.getType() != null && c.getType().equals(CardType.HERO)).collect(toList());
    }

    public List<Card> getHeroPowers() {
        return cards.stream().filter(c -> c.getType() != null && c.getType().equals(CardType.HERO_POWER)).collect(toList());
    }

    public List<Card> getEnchantments() {
        return cards.stream().filter(c -> c.getType() != null && c.getType().equals(CardType.ENCHANTMENT)).collect(toList());
    }

    public List<Card> getDragons() {
        return cards.stream().filter(c -> c.getRace() != null && c.getRace().equals("DRAGON")).collect(toList());
    }

    public List<Card> getAll() {
        return cards;
    }

    public Map<String, List<Card>> groupedByRarity() {
        final Map<String, List<Card>> collect = cards.stream()
                                                     .filter(c -> c.getRarity() != null)
                                                     .collect(Collectors.groupingBy(card -> card.getRarity()));
        return collect;
    }
}
