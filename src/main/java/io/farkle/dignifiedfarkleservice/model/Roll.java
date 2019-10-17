package io.farkle.dignifiedfarkleservice.model;

import java.util.Arrays;
import java.util.Random;

public class Roll {

  static Random rng = new Random();
  // Request from 1-6 return the values of the dice.

  public static int[] rollDice(int generateDiceNumber){
    // create array for rolled dice values

    int [] receiveDice = new int[generateDiceNumber];

    for (int i = 1; i < receiveDice.length; i++ ){

      receiveDice[i] = rng.nextInt(5)+1;

    }

    return receiveDice;


  }

}
