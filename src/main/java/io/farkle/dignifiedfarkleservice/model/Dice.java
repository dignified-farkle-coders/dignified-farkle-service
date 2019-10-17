package io.farkle.dignifiedfarkleservice.model;

import java.util.Objects;
import java.util.Random;

public class Dice {
  private static Random rng = new Random();

  private Face outcome = Face.NULL;

  public void roll(){
    outcome = Face.values()[rng.nextInt(6)];
  }

  public Face getOutcome() {
    return outcome;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dice dice = (Dice) o;
    return outcome == dice.outcome;
  }

  @Override
  public String toString() {
    return outcome.toString();
  }

  public enum Face{

    ONE(100),
    TWO(0),
    THREE(0),
    FOUR(0),
    FIVE(50),
    SIX(0),
    NULL(0);

    int value;

    Face(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return super.toString();
    }
  }
}
