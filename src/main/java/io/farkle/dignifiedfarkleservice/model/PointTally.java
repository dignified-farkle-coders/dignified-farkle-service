package io.farkle.dignifiedfarkleservice.model;

import java.util.Arrays;

public class PointTally {

  public static int DiceTally(int[] keepers){
    int ones = 0;
    int twos = 0;
    int threes = 0;
    int fours = 0;
    int fives = 0;
    int sixes = 0;
    int pointValue = 0;
    int pairs = 0;
    int straight = 0;
    int talliedDice = 0;
    int n = keepers.length - 1;
    int[] amounts = new int[6];
    if (keepers[n] != 0){
      for (int keeper : keepers) {
        if (keeper == 1) {
          ones += 1;
          amounts[0] = ones;
        }
        if (keeper == 2) {
          twos += 1;
          amounts[1] = twos;
        }
        if (keeper == 3) {
          threes += 1;
          amounts[2] = threes;
        }
        if (keeper == 4) {
          fours += 1;
          amounts[3] = fours;
        }
        if (keeper == 5) {
          fives += 1;
          amounts[4] = fives;
        }
        if (keeper == 6) {
          sixes += 1;
          amounts[5] = sixes;
        }
      }
    }

    // Calculates ones and fives
    if (amounts[0] == 1) {
      talliedDice += 1;
      pointValue += 100;
    }
    if (amounts[0] == 2) {
      talliedDice += 2;
      pointValue += 200;
    }
    if (amounts[4] == 1) {
      talliedDice += 1;
      pointValue += 50;
    }
    if (amounts[4] == 2) {
      talliedDice += 2;
      pointValue += 100;
    }

    // Calculates three, four, five, and six of a kind
    for (int i = 0; i < 6 ; i++) {
      if (amounts[i] == 3) {
        talliedDice += 3;
        if (i == 0) {
          pointValue += 300;
        }else
        pointValue += (i + 1) * 100;
      }
      if (amounts[i] == 4) {
        talliedDice += 4;
        if (i == 0) {
          pointValue += 600;
        }else
        pointValue += (i + 1) * 200;
      }
      if (amounts[i] == 5) {
        talliedDice += 5;
        if (i == 0) {
          pointValue += 900;
        }else
        pointValue += (i + 1) * 300;
      }
      if (amounts[i] == 6) {
        talliedDice += 6;
        if (i == 0) {
          pointValue += 1200;
        }else
        pointValue += (i + 1) * 400;
      }
    }

    // Calculates three pairs
    for (int i = 0; i < 6 ; i++) {
      if (amounts[i] == 2) {
        pairs += 1;
      }
    }
    if (pairs == 3) {
      talliedDice += 6;
      pointValue = 1500;
    }

    // Calculates a straight
    for (int i = 0; i < 6 ; i++) {
      if (amounts[i] == 1) {
        straight += 1;
      }
    }
    if (straight == 6) {
      talliedDice += 6;
      pointValue = 3000;
    }
    if (talliedDice == keepers.length && (pointValue != 0)) {
      return pointValue;
    }
    if (talliedDice != keepers.length && (pointValue != 0)) {
      return 1;
    }
    if(pointValue == 0) {
      return 3;
    }
    if ((talliedDice == keepers.length) && (pointValue == 0)) {
      return 0;
    }else return 2;
  }

}
