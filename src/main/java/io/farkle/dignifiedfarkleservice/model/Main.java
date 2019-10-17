package io.farkle.dignifiedfarkleservice.model;

import io.farkle.dignifiedfarkleservice.model.Dice.Face;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.persistence.criteria.CriteriaBuilder.In;

public class Main {

  public static void main(String[] args) {
    String input = "";
    int startingDice = 6;
    ArrayList<Integer> keepers = new ArrayList<Integer>();
    int reroll;

    Scanner scanner = new Scanner(System.in);
    System.out.println("How Many Players?");
    int numberOfPlayers = Integer.parseInt(scanner.nextLine());
    Player [] players = new Player[numberOfPlayers];

    // initialization, set the player array full of player objects
    for (int i = 0; i < players.length; i++) {
      System.out.printf("Who is player number %d?%n",i+1);
      Player player = new Player();
      player.setName(scanner.nextLine());
      players[i] = player;
    }
    System.out.println(Arrays.toString(players));
    System.out.println("First roll: \n");
    System.out.println(Arrays.toString(Roll.rollDice(startingDice)));
    System.out.println("Which die would you like to keep?");
    System.out.println("Type 1-6 to choose. Press 'b' when finished.");

    while(!input.equals("b")) {
      input = scanner.nextLine();
      try {
        int value = Integer.parseInt(input);


      } catch (NumberFormatException e) {
        System.out.println("Oi!!!");
      }

//      keepers.add(value);

      System.out.println(input);
    }
  }
//

//
//    DiceBucket diceBucket = new DiceBucket(6);
//    System.out.println(diceBucket);
//    diceBucket.rollAll();
//    System.out.println(diceBucket);
//    diceBucket.rollAll();
//    System.out.println(diceBucket);
//    diceBucket.rollAll();
//    System.out.println(diceBucket);

}

