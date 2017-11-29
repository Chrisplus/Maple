package com.chrisplus.maple.simulation.players;

import com.chrisplus.maple.simulation.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class Players {

  public static Players from(final List<Player> players) {
    if (players == null || players.size() != 2) {
      throw new IllegalArgumentException();
    }

    Players instance = new Players();
    instance.playerOne = players.get(0);
    instance.playerTwo = players.get(1);
    return instance;
  }

  public static Players create() {
    return new Players();
  }

  private Player playerOne;
  private Player playerTwo;
  private Player currentPlayer;

  private Players() {
    playerOne = new Player();
    playerTwo = new Player();
    currentPlayer = null;
  }

  public final List getPlayers() {
    List<Player> players = new ArrayList<>();
    players.add(playerOne);
    players.add(playerTwo);
    return players;
  }

  public void setRandomCurrentPlayer() {
    int randomInt = Utils.nextRandomInt(0, 1);
    if (randomInt == 0) {
      currentPlayer = playerOne;
    } else {
      currentPlayer = playerTwo;
    }
  }

  public void nextPlayer() {
    if (currentPlayer == playerOne) {
      currentPlayer = playerTwo;
    } else {
      currentPlayer = playerOne;
    }
  }

  public void setCurrentPlayer(Player player) {
    if (player != playerOne && player != playerTwo) {
      throw new IllegalArgumentException();
    }

    currentPlayer = player;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

}
