package io.farkle.dignifiedfarkleservice.model;

public class Player {

  private int playerPoints;
  private String playerName;

  public void setPoints(int points) {
    playerPoints = points;
  }

  public void setName(String name) {
    playerName = name;
  }

  public String getName() {
    return playerName;
  }

  public int getPoints() {
    return playerPoints;
  }

  @Override
  public String toString() {
    return "Players{" +
        "playerName='" + playerName + '\'' +
        '}';
  }
}


