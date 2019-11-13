package io.farkle.dignifiedfarkleservice.model.convert;

import javax.persistence.AttributeConverter;

public class DiceArrayConverter implements AttributeConverter<int[], Integer> {

  private static final int BASE = 7;

  @Override
  public Integer convertToDatabaseColumn(int[] dice) {
    int multiplier = 1;
    int sum = 0;
    for (int die : dice) {
      sum += (die) * multiplier;
      multiplier *= BASE;
    }
    return sum;
  }

  @Override
  public int[] convertToEntityAttribute(Integer value) {
    if (value == null || value == 0) {
      return new int[0];
    }
    int quotient = value;
    int numDice = (int) Math.ceil(Math.log(quotient) / Math.log(BASE));
    int[] dice = new int[numDice];
    int counter = 0;
    while (quotient != 0) {
      int remainder = quotient % BASE;
      quotient /= BASE;
      dice[counter++] = remainder;
    }
    return dice;
  }
}
