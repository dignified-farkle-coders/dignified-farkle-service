package io.farkle.dignifiedfarkleservice.model;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("How Many Players?");
    int noOfPlayers = Integer.parseInt(scanner.nextLine());
//    System.out.println("You said " + noOfPlayers + " players.");
    Player [] players = new Player[noOfPlayers];

    // initialization, set the player array full of player objects
    for (int i = 0; i < players.length; i++) {
      System.out.printf("Who is player number %d?%n",i+1);
      Player player = new Player();
      player.setName(scanner.nextLine());
      player.setPoints(100);
      players[i] = player;
    }
    System.out.println(players[0].getPoints());
    System.out.println(Arrays.toString(players));
  }
}
