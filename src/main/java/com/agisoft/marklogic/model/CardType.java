package com.agisoft.marklogic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CardType {
	HERO, MINION, SPELL, ENCHANTMENT, WEAPON, HERO_POWER,Enchantment, Hero, Minion, Spell, Weapon,
	@JsonProperty("Hero Power") Hero_Power
}
