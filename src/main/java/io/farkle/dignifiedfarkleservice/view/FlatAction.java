package io.farkle.dignifiedfarkleservice.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.farkle.dignifiedfarkleservice.model.entity.Game;
import io.farkle.dignifiedfarkleservice.model.entity.Player;
import java.util.Date;
import org.springframework.lang.NonNull;

public interface FlatAction {

  Long getId();

  Date getCreated();

//  Game getGame();

  @JsonSerialize(as = FlatPlayer.class)
  Player getPlayer();

  @JsonSerialize(as = FlatPlayer.class)
  Player getNextPlayer();

  int[] getAvailableDice();

  int[] getFrozenDice();

  int getTurn();
}
