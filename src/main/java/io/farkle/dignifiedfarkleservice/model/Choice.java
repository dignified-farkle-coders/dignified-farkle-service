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
    int startingDice = randomArray.length;
    boolean doneChoosing = false;
    List<Integer> frozenDice = new ArrayList<>();
    List<Integer> remainingDice = new ArrayList<>();

    int[] tylaArray = Roll.rollDice(startingDice);
    System.out.println(Arrays.toString(tylaArray));

    for (int i : tylaArray) {
      remainingDice.add(i);
    }

    System.out.println("Which die would you like to keep?");
    System.out.println("Type 1-6 to choose. Press 'b' when finished."); // TODO type 1-(available dice left)


    while (!doneChoosing) {
      Scanner scanner = new Scanner(System.in);

      String frozenDieString = scanner.nextLine();
      // TODO Dont allow user to input String != 'b'

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





