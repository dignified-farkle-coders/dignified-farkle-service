package io.farkle.dignifiedfarkleservice.model;

import io.farkle.dignifiedfarkleservice.model.Dice.Face;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.persistence.criteria.CriteriaBuilder.In;

public class Main {

  public static void main(String[] args) {
    int startingDice = 6;

    Scanner scanner = new Scanner(System.in);
    System.out.println("How Many Players?");
    int numberOfPlayers = Integer.parseInt(scanner.nextLine());
    Player[] players = new Player[numberOfPlayers];

    // initialization, set the player array full of player objects
    for (int i = 0; i < players.length; i++) {
      System.out.printf("Who is player number %d?%n", i + 1);
      Player player = new Player();
      player.setName(scanner.nextLine());
      players[i] = player;
    }

    System.out.println(Arrays.toString(players));
    System.out.println("First roll: \n");
    System.out.println(Arrays.toString(Roll.rollDice(6)));
    int[] test = Roll.rollDice(6);

    List<Integer> tylaMethod = new ArrayList<>();
    System.out.println("Which die would you like to keep?");
    System.out.println("Type 1-6 to choose. Press 'b' when finished.");


  }


}

