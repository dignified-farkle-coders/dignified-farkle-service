package io.farkle.dignifiedfarkleservice.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Choice {

  private static int[] keepers;
  private static int reroll;


  public Choice(int[] keepers, int reroll) {
    Choice.keepers = keepers;
    Choice.reroll = reroll;
  }

  public static int[] getKeepers() {
    return keepers;
  }

  public static int getReroll() {
    return reroll;
  }

  @Override
  public String toString() {
    return "Keep: " + Arrays.toString(getKeepers()) + ", Reroll:" + getReroll();
  }

//  public static Choice Keepers(){
//    return nullgetKeepers();
//  }

  public static Choice remainingDice(int[] randomArray) {
    int startingDice = 6;
    List<Integer> frozenDice = new ArrayList<>();
    int[] tylaArray = Roll.rollDice(startingDice);
    System.out.println(Arrays.toString(tylaArray));
    List<Integer> remainingDice = new ArrayList<>();

    for(int i : tylaArray) {
      remainingDice.add(i);
    }

    System.out.println("Which die would you like to keep?");
    System.out.println("Type 1-6 to choose. Press 'b' when finished.");

    while (frozenDice.size() < 6) {
      Scanner freeze = new Scanner(System.in);
      int frozenDie = Integer.parseInt(freeze.nextLine());
      frozenDice.add(remainingDice.get(frozenDie - 1));
      remainingDice.remove(frozenDie - 1);
      System.out.println(remainingDice);
      System.out.println(frozenDice);
    }
    Choice choice = new Choice(frozenDice.stream().mapToInt(Integer::valueOf).toArray(), remainingDice.size());
    return choice;
  }

}





