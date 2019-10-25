package io.farkle.dignifiedfarkleservice.model;

import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

  public static int diceAmount = 6;
  public static boolean reroll = true;

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("How Many Players?");
    int numberOfPlayers = Integer.parseInt(scanner.nextLine());
    Player[] players = new Player[numberOfPlayers];




    // initialization, set the player array full of player objects.
    for (int i = 0; i < players.length; i++) {
      System.out.printf("Who is player number %d?%n", i + 1);
      Player player = new Player();
      player.setName(scanner.nextLine());
      players[i] = player;
    }

    Iterator<Player> playerIterator = Iterables.cycle(Arrays.asList(players)).iterator();
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(playerIterator.next());
    System.out.println(Arrays.toString(players));
    System.out.println("First roll: \n");

    //Displays current dice.
    System.out.println(Choice.remainingDice(Roll.rollDice(6)));
    System.out.println("This is your score: " + PointTally.DiceTally(Choice.getKeepers()));

    while (reroll) {
      System.out.println("Do you wish to re-roll? (y/n)");
      diceAmount = Choice.getReroll();
      String yesToReroll = scanner.nextLine();
      if (yesToReroll.equals("y")) {
        System.out
            .println("These are your new dice: " + Choice.remainingDice(Roll.rollDice(diceAmount)));
        System.out.println("Points gained! " + PointTally.DiceTally(Choice.getKeepers()));
      }

      if (yesToReroll.equals("n")) {
        System.out.println("Next players turn.");
        reroll = false;
      }
    }
    // TODO If player gets value for all 6 die, reroll all 6.
    // TODO Implement next players turn.

  }

}




