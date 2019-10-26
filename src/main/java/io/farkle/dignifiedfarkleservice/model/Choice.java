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

  public static Choice remainingDice(int[] randomArray) {
    int [] tylaArray = new int[randomArray.length];
    int startingDice = randomArray.length;
    boolean doneChoosing = false;
    List<Integer> frozenDice = new ArrayList<>();
    List<Integer> remainingDice = new ArrayList<>();

    if (randomArray[0] != 7) {
      tylaArray = Roll.rollDice(startingDice);
    }

    // Checks if all dice have point value and no more dice left to roll, granting 6 new dice.
    if (randomArray[0] == 7 && PointTally.DiceTally(getKeepers()) % 10 == 0) {
      tylaArray = Roll.rollDice(6);
    }

    System.out.println(Arrays.toString(tylaArray));

    if (PointTally.DiceTally(tylaArray) == 1) {
      System.out.println("Loser Bruiser!");
      return new Choice(new int[]{}, 0);
    }

    for (int i : tylaArray) {
      remainingDice.add(i);
    }

    System.out.println("Which die would you like to keep?");
    System.out.println("Type 1-" + tylaArray.length +" to choose. Press 'b' when finished.");


    while (!doneChoosing) {
      Scanner scanner = new Scanner(System.in);

      String frozenDieString = scanner.nextLine();

      int frozenDieInt;

      try {
        frozenDieInt = Integer.parseInt(frozenDieString);
        frozenDice.add(remainingDice.get(frozenDieInt - 1));
        remainingDice.remove(frozenDieInt - 1);
        System.out.println(remainingDice);
        System.out.println(frozenDice);

      } catch (NumberFormatException ignored) {
        if (frozenDieString.equals("b")) {

          System.out.println("You have choosen b");
          doneChoosing = true;
          break;
        }

      }

    }
    Choice choice = new Choice(frozenDice.stream().mapToInt(Integer::valueOf).toArray(),
        remainingDice.size());
    return choice;
  }

//  public static Choice Keepers(){
//    return nullgetKeepers();
//  }

  @Override
  public String toString() {
    return "Keep: " + Arrays.toString(getKeepers()) + ", Reroll:" + getReroll();
  }

}





