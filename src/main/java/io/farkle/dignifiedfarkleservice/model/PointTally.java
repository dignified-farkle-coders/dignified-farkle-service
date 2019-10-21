package io.farkle.dignifiedfarkleservice.model;

import java.util.Arrays;

public class PointTally {

  public static int pointTally(int[] keepers){
    System.out.println("Keepers!!!:" + Arrays.toString(Choice.getKeepers()));
    int ones = 0;
    int twos = 0;
    int threes = 0;
    int fours = 0;
    int fives = 0;
    int sixes = 0;
    int n = keepers.length - 1;
    if (keepers[n] == 5){
      for (int i = 0; i < keepers.length ; i++) {
        if (keepers[i] == 1) {
          ones += 1;
        }
        if (keepers[i] == 2) {
          twos += 1;
        }
        if (keepers[i] == 3) {
          threes += 1;
        }
        if (keepers[i] == 4) {
          fours += 1;
        }
        if (keepers[i] == 5) {
          fives += 1;
        }
        if (keepers[i] == 6) {
          sixes += 1;
        }
      }
    }

    return 3;
  }

}
