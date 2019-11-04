package io.farkle.dignifiedfarkleservice.model;

import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

  public static int diceAmount = 6;
  public static boolean reroll = true;

  public static void main(String[] args) {
    boolean catchPass = true;
    int pointValue = 0;

    Scanner scanner = new Scanner(System.in);
    System.out.println("How Many Players?");
    int numberOfPlayers = Integer.parseInt(scanner.nextLine());
    Player[] players = new Player[numberOfPlayers];

    // initialization, set the player array full of player objects.
    for (int i = 0; i < players.length; i++) {
      Player player = new Player();
      System.out.printf("Who is player number %d?%n", i + 1);
      player.setName(scanner.nextLine());
      players[i] = player;
    }

    Iterator<Player> playerIterator = Iterables.cycle(Arrays.asList(players)).iterator();
    //////////////////////////////////////////////////////////////////////////////////// Game Begins
    int x = 0;

    while (true) {
      int getPlayerScore = players[x].getPoints();

      while (catchPass) {
        reroll = true;
        int diceAmount = 6;
        String playerName = playerIterator.next().getName();
        String playerShort = playerName.substring(0, 1).toUpperCase() + playerName.substring(1);
        System.out.println(playerShort + " Total Points: " + getPlayerScore);

        System.out.println(playerShort + "'s turn.");
        System.out.println("First roll: \n");

        //Displays current dice.

        System.out.println(Choice.remainingDice(Roll.rollDice(6)));
        try {
          pointValue = PointTally.DiceTally(Choice.getKeepers());
          System.out.println("This is your score: " + pointValue);
          getPlayerScore += pointValue;

          catchPass = false;
        } catch (Exception e) {
          System.out.println("Wow, lost on the first try?!");
          playerIterator.next().setPoints(0);
          playerShort = playerName.substring(0, 1).toUpperCase() + playerName.substring(1);
          System.out.println(playerShort + " Total Points: " + getPlayerScore);
          System.out.println(
              playerName.substring(0, 1).toUpperCase() + playerName.substring(1) + "'s turn.");
          System.out.println("First roll: \n");
          System.out.println(Choice.remainingDice(Roll.rollDice(6)));
        }
      }

      while (reroll) {
        catchPass = true;
        System.out.println("Do you wish to re-roll? (y/n)");

        diceAmount = Choice.getReroll();

        String yesToReroll = scanner.nextLine();
        if (yesToReroll.equals("y")) {
          try {
            System.out.println(Choice.remainingDice(Roll.rollDice(diceAmount)));
            pointValue = PointTally.DiceTally((Choice.getKeepers()));
            getPlayerScore += pointValue;
            System.out.println("Points gained: " + pointValue);
          } catch (Exception e) {
            System.out.println("Welp, that sucks");
            reroll = false;
            break;
          }
        }

        if (yesToReroll.equals("n")) {
          players[x].setPoints(getPlayerScore);
          x = x + 1;
          if (x == players.length) {
            x = 0;
          }
          reroll = false;
        }
      }
    }
  }
}




