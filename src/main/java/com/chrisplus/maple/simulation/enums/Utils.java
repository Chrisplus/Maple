package com.chrisplus.maple.simulation.enums;

import com.chrisplus.maple.simulation.enums.CardSetOuterClass.CardSet;
import com.chrisplus.maple.simulation.enums.CardTypeOuterClass.CardType;
import com.chrisplus.maple.simulation.enums.RaceOuterClass.Race;
import com.chrisplus.maple.simulation.enums.RarityOuterClass.Rarity;
import java.util.EnumSet;

public class Utils {

  private Utils() {

  }

  public static boolean isCraftable(CardSet cardset) {
    return EnumSet
        .of(CardSet.CARDSET_EXPERT1, CardSet.CARDSET_NAXX, CardSet.CARDSET_GVG, CardSet.CARDSET_BRM,
            CardSet.CARDSET_TGT,
            CardSet.CARDSET_LOE, CardSet.CARDSET_OG, CardSet.CARDSET_KARA, CardSet.CARDSET_GANGS,
            CardSet.CARDSET_UNGORO).contains(cardset);
  }

  public static boolean isStandard(CardSet cardSet) {
    return EnumSet.of(CardSet.CARDSET_CORE, CardSet.CARDSET_EXPERT1, CardSet.CARDSET_OG, CardSet
        .CARDSET_KARA, CardSet.CARDSET_GANGS, CardSet.CARDSET_UNGORO, CardSet.CARDSET_ICECROWN)
        .contains(cardSet);
  }

  public static boolean isCraftable(CardType cardType) {
    return EnumSet.of(CardType.CARDTYPE_MINION, CardType.CARDTYPE_SPELL, CardType
        .CARDTYPE_WEAPON).contains(cardType);
  }

  public static boolean isRaceVisible(Race race) {
    return EnumSet.of(Race.RACE_MURLOC, Race.RACE_DEMON, Race.RACE_MECHANICAL, Race.RACE_BEAST,
        Race.RACE_TOTEM, Race.RACE_PIRATE, Race.RACE_DRAGON).contains(race);
  }

  public static boolean isCraftable(Rarity rarity){
    return EnumSet.of(Rarity.RARITY_COMMON, Rarity.RARITY_RARE, Rarity.RARITY_EPIC, Rarity
        .RARITY_LEGENDARY).contains(rarity);
  }

}
