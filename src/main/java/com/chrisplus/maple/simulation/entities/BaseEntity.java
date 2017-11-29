package com.chrisplus.maple.simulation.entities;

import com.chrisplus.maple.simulation.enums.CardTypeOuterClass.CardType;
import com.chrisplus.maple.simulation.managers.BaseManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BaseEntity {

  private List<Object> events;

  // TODO add logging functions
  // private Logger logger;

  protected boolean ignoreScripts = false;
  protected CardType type = CardType.CARDTYPE_INVALID;
  protected BaseManager manager;
  protected int playCounter;
  protected UUID uuid;

  public BaseEntity() {
    this.events = new ArrayList<>();
    this.ignoreScripts = false;
    this.type = CardType.CARDTYPE_INVALID;
    this.manager = createManager();
    this.uuid = UUID.randomUUID();
  }

  abstract protected BaseManager createManager();

  public List<Object> getEvents() {
    return events;
  }

  public boolean isIgnoreScripts() {
    return ignoreScripts;
  }

  public CardType getType() {
    return type;
  }

  public int getPlayCounter() {
    return playCounter;
  }

  public UUID getUuid() {
    return uuid;
  }

  public boolean isCard() {
    return CardType.CARDTYPE_PLAYER != this.type && CardType.CARDTYPE_GAME != this.type;
  }


}
