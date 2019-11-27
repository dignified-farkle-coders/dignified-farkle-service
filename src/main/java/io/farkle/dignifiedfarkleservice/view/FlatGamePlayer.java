package io.farkle.dignifiedfarkleservice.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.farkle.dignifiedfarkleservice.model.entity.Player;

public interface FlatGamePlayer {

  Long getId();

  @JsonSerialize(as = FlatPlayer.class)
  Player getPlayer();

  int getOrder();

  int getPoints();

}
