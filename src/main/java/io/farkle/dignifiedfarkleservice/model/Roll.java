package io.farkle.dignifiedfarkleservice.model;

import java.util.Arrays;
import java.util.Random;


public class Roll {

  static Random rng = new Random();


  // Request from 1-6 return the values of the dice.

  public static int[] rollDice(int generateDiceNumber){
    // create array for rolled dice values
    int myRandomNumber;
    int [] receiveDice = new int[generateDiceNumber];

    if (generateDiceNumber == 0) {
      return new int[]{7};
    }

    for (int i = 0; i < generateDiceNumber; i++) {
      myRandomNumber = rng.nextInt(6) + 1;
      receiveDice[i] = myRandomNumber;
    }
    return receiveDice;
  }

}