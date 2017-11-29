package com.chrisplus.maple.simulation.games;

import com.chrisplus.maple.simulation.cards.CardList;
import com.chrisplus.maple.simulation.entities.BaseEntity;
import com.chrisplus.maple.simulation.enums.StateOuterClass.State;
import com.chrisplus.maple.simulation.enums.StepOuterClass.Step;
import com.chrisplus.maple.simulation.managers.BaseManager;
import com.chrisplus.maple.simulation.managers.GameManager;
import com.chrisplus.maple.simulation.players.Player;
import com.chrisplus.maple.simulation.players.Players;
import java.util.List;

public class Game extends BaseEntity{

  public static final int MAX_MINIONS_ON_FIELD = 7;

  public State state;
  public Step step;
  public Step nextStep;
  public int turn;
  public int tick;
  public Players players;
  public CardList activeAuraBuffs;
  public CardList setAside;

  public Game() {
    this(Players.create());

  }

  public Game(List<Player> players) {
    this(Players.from(players));
  }

  public Game(Players players) {
    super();

    this.players = players;
    this.state = State.STATE_INVALID;
    this.step = Step.STEP_BEGIN_FIRST;
    this.nextStep = Step.STEP_BEGIN_SHUFFLE;
    this.turn = 0;
    this.tick = 0;
    this.activeAuraBuffs = new CardList();
    this.setAside = new CardList();
  }

  @Override
  protected BaseManager createManager() {
    return new GameManager();
  }
}
