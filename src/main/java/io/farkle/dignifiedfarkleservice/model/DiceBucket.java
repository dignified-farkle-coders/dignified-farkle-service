package io.farkle.dignifiedfarkleservice.model;

import java.util.ArrayList;
import java.util.List;

public class DiceBucket {

  private List<Dice> diceBucket = new ArrayList<>();

  public DiceBucket(int n) {
    for (int i = 0; i < n; i++) {
      add(new Dice());
    }
  }


  public List<Dice> getDiceBucket() {
    return diceBucket;
  }

  public boolean add(Dice dice) {
    return diceBucket.add(dice);
  }

  public void rollAll(){
    for (Dice dice : diceBucket) {
      dice.roll();
    }
  }

  @Override
  public String toString() {
    return diceBucket.toString();
  }
}
