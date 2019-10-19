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
    List<Integer> frozenDice = new ArrayList<>();

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
    int[] tylaArray = Roll.rollDice(startingDice);
    List<Integer> remainingDice = new ArrayList<>();
    for(int i : tylaArray) {
      remainingDice.add(i);
    }
    System.out.println(remainingDice);
    System.out.println("Which die would you like to keep?");
    System.out.println("Type 1-6 to choose. Press 'b' when finished.");


    while (frozenDice.size() < 6) {
      Scanner freeze = new Scanner(System.in);
      int frozenDie = Integer.parseInt(freeze.nextLine());
      frozenDice.add(tylaArray[frozenDie]);
      remainingDice.remove(frozenDie);
      System.out.println(remainingDice);
      System.out.println(frozenDice);
      }

    }

}




